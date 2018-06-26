package com.smartcontrol.smartcontrol.api

import com.smartcontrol.smartcontrol.helper.JSoupHelper
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import io.reactivex.Single
import okhttp3.Credentials
import okhttp3.Request
import xyz.blackice.rxokhttp.RxOkHttp
import javax.inject.Inject

class SmartControlApi @Inject constructor(val jSoupHelper: JSoupHelper){

    fun pressRelay(host: String, username : String, password: String) : Single<String> {
        val credential = Credentials.basic(username, password)
        val builder = Request.Builder()
                .url(host)
                .header("Authorization", credential)
                .get()
        return RxOkHttp.instance().get(builder.build())
                .single("")
    }

    fun getRelay(board: Board) : Single<List<Relay>> {
        return RxOkHttp.instance().get(buildRequest(board))
                .single("")
                .map {
                    mapperHtmlToRelay(it, board.id)
                }

    }

    fun getRelayStatus(board: Board) : Single<String> {
        val request = buildRequest(board.host + STATUS, board)
        return RxOkHttp.instance().get(request)
                .single("")
    }
    
    private fun buildRequest(url : String?, board: Board) : Request {
        return buildRequest(url, board.username, board.password)
    }

    private fun buildRequest(board: Board) : Request {
        return buildRequest(board.host, board.username, board.password)
    }

    private fun buildRequest(url : String?, username: String?, password: String?) : Request {
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