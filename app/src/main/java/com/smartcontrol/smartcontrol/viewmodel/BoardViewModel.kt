package com.smartcontrol.smartcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import com.smartcontrol.smartcontrol.repository.BoardRepository
import com.smartcontrol.smartcontrol.repository.TwitRepository
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class BoardViewModel @Inject constructor(private val boardRepository: BoardRepository) : ViewModel() {

    private val boardLiveData : LiveData<List<Board>> = LiveDataReactiveStreams.fromPublisher(boardRepository.getAll())
    private val boardStatusLiveData : MutableLiveData<List<Board>> = MutableLiveData()
    private val modelCheckStatusFinish : MutableLiveData<Boolean> = MutableLiveData()

    fun getBoards() : LiveData<List<Board>> {
        return boardLiveData
    }
    fun getModelCheckStatus() : LiveData<Boolean> {
        return modelCheckStatusFinish
    }
    fun getStatusBoards() : LiveData<List<Board>> {
        return boardStatusLiveData
    }

    fun insertBoard(board: Board) {
        boardRepository.insert(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun updateBoard(board: Board) {
        boardRepository.update(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun updateAll(board: List<Board>) {
        boardRepository.updateAll(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun deleteBoard(board: Board) {
        boardRepository.delete(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun checkStatus() {
        modelCheckStatusFinish.value = false
        boardLiveData.value?.let {
            boardRepository.checkStatus(it)
                    .subscribe { t1, t2 ->
                        modelCheckStatusFinish.value = true
                        t1?.let {
                            if (isChangeStatus(boardLiveData.value, t1)) {
                                boardStatusLiveData.value = t1
                                updateAll(t1)
                            }
                        }
                        t2?.printStackTrace()
                    }
        }
    }

    private fun isChangeStatus(boardList1: List<Board>?, boardList2: List<Board>?) : Boolean {
        if (boardList2 == null) {
            return true
        }
        if (boardList1?.size != boardList2?.size ) {
            return true
        }
        boardList1?.forEachIndexed { index, board1 ->
            val board2 = boardList2?.get(index)
            return board1?.status != board2?.status
        }
        return false
    }

}