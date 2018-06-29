package com.smartcontrol.smartcontrol.repository

import com.smartcontrol.smartcontrol.api.SmartControlApi
import com.smartcontrol.smartcontrol.db.BoardDao
import com.smartcontrol.smartcontrol.db.RelayDao
import com.smartcontrol.smartcontrol.db.TwitDao
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class BoardRepository @Inject constructor(
        private val smartControlApi: SmartControlApi,
        private val boardDao: BoardDao) {

    fun delete(board: Board) : Completable {
        return Completable.create { boardDao.delete(board) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
    fun update(board: Board) : Completable {
        return Completable.create { boardDao.update(board) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun insert(board: Board) : Completable {
        return Completable.create { boardDao.insert(board) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun checkStatus(board: Board) : Flowable<Board> {
        return smartControlApi.checkBoard(board)
                .onErrorResumeNext(ObservableSource { Observable.just(false) })
                .map {
                    board.status = it
                    return@map board
                }
                .toFlowable(BackpressureStrategy.BUFFER)
    }

    fun checkStatus(boardList: List<Board>) : Single<List<Board>> {
        return Flowable.fromIterable(boardList)
                .flatMap { checkStatus(it) }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getAll() : Flowable<List<Board>> {
        return boardDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }




}