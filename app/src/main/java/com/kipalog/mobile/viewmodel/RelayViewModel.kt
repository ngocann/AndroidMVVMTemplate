package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay
import com.kipalog.mobile.repository.RelayRepository
import com.kipalog.mobile.util.Log
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
            relayDisposable?.dispose()
        }
    }

}