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

    fun getBoards() : LiveData<List<Board>> {
        return boardLiveData
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

    fun deleteBoard(board: Board) {
        boardRepository.delete(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun checkStatus() {
        boardLiveData.value?.let {
            boardRepository.checkStatus(it)
                    .subscribe { t1, t2 ->
                        t1?.let {
                            boardStatusLiveData.value = t1
                        }
                        t2?.printStackTrace()
                    }
        }
    }

}