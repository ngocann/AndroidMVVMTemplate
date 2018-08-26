package com.kipalog.mobile.api

import com.kipalog.mobile.helper.JSoupHelper
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.Request
import xyz.blackice.rxokhttp.RequestBuilder
import xyz.blackice.rxokhttp.RxOkHttp
import javax.inject.Inject

class SmartControlApi @Inject constructor(private val jSoupHelper: JSoupHelper){

    fun pressRelay(port: String?, board: Board) : Completable {
        return RxOkHttp.instance().get(buildRequest("${board.host}/$port", board))
                .ignoreElements()
    }


    fun checkBoard(board: Board) : Observable<Boolean> {
        val request = buildRequest(board.host + STATUS, board)
        val requestBuilder = RequestBuilder()
        requestBuilder.timeout = 2
        return RxOkHttp(requestBuilder).get(request)
                .map { return@map true }
    }
    fun getRelay(board: Board) : Single<List<Relay>> {
        return RxOkHttp.instance().get(buildRequest(board))
                .single("")
                .map {
                    mapperHtmlToRelay(it, board.id)
                }
    }

    fun getRelayStatus(board: Board) : Observable<String> {
        val request = buildRequest(board.host + STATUS, board)
        return RxOkHttp.instance().get(request)
    }
    
    private fun buildRequest(url : String?, board: Board) : Request? {
        return buildRequest(url, board.username, board.password)
    }

    private fun buildRequest(board: Board) : Request? {
        return buildRequest(board.host, board.username, board.password)
    }

    private fun buildRequest(url : String?, username: String?, password: String?) : Request? {
        val credential = Credentials.basic(username, password)
        val builder = Request.Builder()
                .url(url)
                .header("Authorization", credential)
                .get()
        return builder.build()
    }


    private fun mapperHtmlToStatus(html : String) : List<Boolean> {
        val elements = jSoupHelper.parse(html, "led*")

        val statusList = ArrayList<Boolean>()
        elements.forEach {
            statusList.add(it.text() != "0")
        }
        return statusList


    }
    private fun mapperHtmlToRelay(html : String, idBoard : Long?) : List<Relay> {
        val elements = jSoupHelper.parse(html, "#display").select("input")
        val relayList = ArrayList<Relay>()
        elements.forEach {
            val name = it.attr("value")
            val led = it.attr("id")
            val port = it.attr("onclick")
                    .replace("newAJAXCommand('","")
                    .replace("');","")
            relayList.add(Relay(null,idBoard , port, name, led))
        }
        return relayList
    }

    companion object {
        const val STATUS = "/status.xml"
    }
}