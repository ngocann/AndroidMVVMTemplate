package com.smartcontrol.smartcontrol.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit
import com.smartcontrol.smartcontrol.repository.RelayRepository
import com.smartcontrol.smartcontrol.repository.TwitRepository
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RelayViewModel @Inject constructor(private val relayRepository: RelayRepository) : ViewModel() {

    var relayLiveData : MutableLiveData<List<Relay>> = MutableLiveData()
    private lateinit var board: Board
    private var relayDisposable : Disposable? = null
    fun setBoard(board: Board) {
        this.board = board
        getRelay()
    }

    fun getRelay() {
        relayRepository.getRelay(board)
                .subscribe { t1, t2 ->
                    t1?.let {
                        relayLiveData.value = t1
                        getRelayStatus()
                    }
                    t2?.printStackTrace()
                }
    }

    fun getRelayStatus() {
        if (relayDisposable != null) {
            relayDisposable?.dispose()
        }
        relayDisposable = relayRepository.getRelayStatus(board, relayLiveData.value)
                .subscribe({
                    if (it.first) {
                        relayLiveData.value = it.second
                    }
                }, {it.printStackTrace()})
    }

    fun pressRelay(relay : Relay) {
        relayRepository.pressRelay(relay, board)
                .subscribe()
    }

    fun destroy() {
        if (relayDisposable != null) {
//            relayDisposable?.dispose()
        }
    }

}