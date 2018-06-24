package com.smartcontrol.smartcontrol.repository

import com.smartcontrol.smartcontrol.api.SmartControlApi
import com.smartcontrol.smartcontrol.db.BoardDao
import com.smartcontrol.smartcontrol.db.RelayDao
import com.smartcontrol.smartcontrol.db.TwitDao
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardRepository @Inject constructor(
        private val smartControlApi: SmartControlApi,
        private val boardDao: BoardDao) {

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

    fun getAll() : Flowable<List<Board>> {
        return boardDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }
}