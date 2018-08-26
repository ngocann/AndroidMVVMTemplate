package com.kipalog.mobile.repository

import com.kipalog.mobile.api.SmartControlApi
import com.kipalog.mobile.db.RelayDao
import com.kipalog.mobile.helper.JSoupHelper
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class RelayRepository @Inject constructor(
        private val smartControlApi: SmartControlApi,
        private val relayDao: RelayDao, private val jSoupHelper: JSoupHelper) {

    //http://opensmarthome.ddns.eagleeyes.tw:98/leds.cgi?led=1
    //http://opensmarthome.ddns.eagleeyes.tw:98/status.xml

    fun pressRelay(relay: Relay, board: Board) : Completable {
        return smartControlApi.pressRelay(relay.port, board)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
    fun getRelay(board: Board) : Single<List<Relay>> {
        return smartControlApi
                .getRelay(board)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }


    fun getRelayStatus(board: Board, relayList: List<Relay>?) : Observable<Pair<Boolean, List<Relay>?>> {
        return Observable.interval(0, TimeUnit.SECONDS)
                .flatMap { _ ->
                    return@flatMap smartControlApi.getRelayStatus(board)
                            .map { mapperHtmlToStatus(it, relayList ) }
                }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    private fun mapperHtmlToStatus(html : String, relayList: List<Relay>?) : Pair<Boolean, List<Relay>?> {
        val document = jSoupHelper.parse(html)
        var isUpdated = false
        relayList?.forEach {
            val status = document.select(it.led).text() != "0"
            if (status != it.status) {
                it.status = status
                isUpdated = true
            }
        }
        return Pair(isUpdated, relayList)

    }





}