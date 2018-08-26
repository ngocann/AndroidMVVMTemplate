package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.text.Editable
import android.text.TextUtils
import android.util.Patterns
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.repository.BoardRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class AddBoardViewModel @Inject constructor(private val boardRepository: BoardRepository) : ViewModel() {

    private val boardLiveData : LiveData<List<Board>> = LiveDataReactiveStreams.fromPublisher(boardRepository.getAll())
    private val messageLiveData = MutableLiveData<String>()

    var board : Board? = null

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
                .subscribe { Log.d("Complete update") }
    }

    fun saveBoard(host: Editable, username: Editable, pass: Editable, name: Editable, completer: (() -> Unit)) {
        if (!isHostValid(host)) {
            messageLiveData.value = "Tên miền không hợp lệ! Ví dụ: http://smartcontrol.vn:9090"
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

        if (board == null) {
            insertBoard(Board(null, name.toString(), host.toString(), username.toString(), pass.toString()))
        }else {
            board?.name = name.toString()
            board?.host = host.toString()
            board?.username = username.toString()
            board?.password = pass.toString()
            updateBoard(board!!)
        }
        completer()
    }

    private fun isHostValid(value : Editable) : Boolean {
        if (!isStringValid(value)) {
            return false;
        }
        return value.toString().startsWith("http://")
                || value.toString().startsWith("https://")
    }
    private fun isStringValid(value : Editable) : Boolean {
        return when {
            TextUtils.isEmpty(value) -> false
            else -> true
        }
    }

}