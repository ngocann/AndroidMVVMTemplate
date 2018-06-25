package com.smartcontrol.smartcontrol.repository

import com.smartcontrol.smartcontrol.api.SmartControlApi
import com.smartcontrol.smartcontrol.db.RelayDao
import com.smartcontrol.smartcontrol.db.TwitDao
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
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

    //http://opensmarthome.ddns.eagleeyes.tw:98/leds.cgi?led=1
    //http://opensmarthome.ddns.eagleeyes.tw:98/status.xml

    fun getRelay(board: Board) : Single<List<Relay>> {
        return smartControlApi
                .getRelay(board)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }






}