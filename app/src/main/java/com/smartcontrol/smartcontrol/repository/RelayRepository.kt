package com.smartcontrol.smartcontrol.repository

import com.smartcontrol.smartcontrol.api.SmartControlApi
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

class RelayRepository @Inject constructor(
        private val smartControlApi: SmartControlApi,
        private val relayDao: RelayDao) {

    fun getRelay(board: Board) : Single<String> {
        return smartControlApi
                .getRelay(board.host!!, board.username!!, board.password!!)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}