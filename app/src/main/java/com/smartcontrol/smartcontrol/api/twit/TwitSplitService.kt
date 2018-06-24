package com.smartcontrol.smartcontrol.api.twit

import java.util.*

class TwitSplitService {

    private val DELIMETER = " "
    private val MAX_MESSAGE = 50

    private var wordList : List<String> = ArrayList()

    fun splitMessage (message : String) : List<String> {
        if ( message.trim().length <= MAX_MESSAGE ) {
            return Arrays.asList(message.trim())
        }
        wordList = message.trim().split(DELIMETER)

        var twitMessageList = ArrayList<TwitMessage>()
        var indexPage = 1
        var twitMessage = TwitMessage(indexPage, 0)

        for (word in wordList) {
            if (!twitMessage.checkLargerAndAdd(word)) {
                twitMessageList.add(twitMessage)
                twitMessage = TwitMessage(++indexPage, 0, word)
            }
        }
        twitMessageList.add(twitMessage)

        twitMessageList.map { it.total = indexPage }

        return twitMessageList.map { it.toString() }
    }

}