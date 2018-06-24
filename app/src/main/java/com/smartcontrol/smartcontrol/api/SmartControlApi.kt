package com.smartcontrol.smartcontrol.api

import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.util.Log
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.Request
import xyz.blackice.rxokhttp.RxOkHttp
import javax.inject.Inject

class SmartControlApi @Inject constructor(){

    fun pressRelay(host: String, username : String, password: String) : Single<String> {
        val credential = Credentials.basic(username, password)
        val builder = Request.Builder()
                .url(host)
                .header("Authorization", credential)
                .get()
        return RxOkHttp.instance().get(builder.build())
                .single("")
    }

    fun getRelay(host: String, username : String, password: String) : Single<String>{
        val credential = Credentials.basic(username, password)
        val builder = Request.Builder()
                .url(host)
                .header("Authorization", credential)
                .get()
        return RxOkHttp.instance().get(builder.build())
                .single("")
                .map {
                    Log.d("getrelay $it")
                    it
                }

    }
}