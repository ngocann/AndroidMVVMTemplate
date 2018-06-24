package com.smartcontrol.smartcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit
import com.smartcontrol.smartcontrol.repository.RelayRepository
import com.smartcontrol.smartcontrol.repository.TwitRepository
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RelayViewModel @Inject constructor(private val relayRepository: RelayRepository) : ViewModel() {

    private lateinit var relayLiveData : LiveData<List<Relay>>
    private lateinit var board: Board

    fun setBoard(board: Board) {
        this.board = board
        getRelay(board)
    }

    fun getRelay(board: Board) {
        relayRepository.getRelay(board)
                .subscribe { t1, t2 ->
                    Log.d("getRelays $t1")
                    if (t2 != null) {
                        t2.printStackTrace()
                    }
                }
    }

}