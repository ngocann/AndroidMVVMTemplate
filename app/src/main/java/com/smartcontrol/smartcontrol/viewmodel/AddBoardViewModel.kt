package com.smartcontrol.smartcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextUtils
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.repository.BoardRepository
import com.smartcontrol.smartcontrol.util.Log
import javax.inject.Inject

class AddBoardViewModel @Inject constructor(private val boardRepository: BoardRepository) : ViewModel() {

    private val boardLiveData : LiveData<List<Board>> = LiveDataReactiveStreams.fromPublisher(boardRepository.getAll())
    private val messageLiveData = MutableLiveData<String>()


    fun getMessageLiveData(): MutableLiveData<String> {
        return messageLiveData
    }
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

    fun addBoard(host: Editable, username: Editable, pass: Editable, name: Editable, completer: (() -> Unit)) {
        if (!isStringValid(host)) {
            messageLiveData.value = "Tên miền không hợp lệ!"
            return
        }
        if (!isStringValid(username)) {
            messageLiveData.value = "Tên đăng nhập không hợp lệ!"
            return
        }
        if (!isStringValid(pass)) {
            messageLiveData.value = "Mật khẩu không hợp lệ!"
            return
        }

        if (!isStringValid(name)) {
            messageLiveData.value = "Tên không hợp lệ!"
            return
        }

        insertBoard(Board(null, name.toString(), host.toString(), username.toString(), pass.toString()))
        completer()
    }

    private fun isStringValid(value : Editable) : Boolean {
        return when {
            TextUtils.isEmpty(value) -> false
            else -> true
        }
    }

}