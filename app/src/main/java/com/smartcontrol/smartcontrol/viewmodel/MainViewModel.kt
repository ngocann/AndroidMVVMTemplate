package com.smartcontrol.smartcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.ViewModel
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import com.smartcontrol.smartcontrol.repository.BoardRepository
import com.smartcontrol.smartcontrol.repository.TwitRepository
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val boardRepository: BoardRepository) : ViewModel() {

    private val boardLiveData : LiveData<List<Board>> = LiveDataReactiveStreams.fromPublisher(boardRepository.getAll())

    fun getBoards() : LiveData<List<Board>> {
        return boardLiveData
    }

    fun insertBoard(board: Board) {
        boardRepository.insert(board)
                .subscribe { Log.d("Complete insert") }
    }

    fun updateBoard(board: Board) {
        boardRepository.update(board)
                .subscribe { Log.d("Complete insert") }
    }

}