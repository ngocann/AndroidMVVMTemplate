package com.smartcontrol.smartcontrol.api.twit

import org.junit.Test

import org.junit.Assert.*

class TwitSplitServiceTest {

    @Test
    fun splitMessage() {

        var twitSplit = TwitSplitService()
        var message = "I can't believe Tweeter now supports chunking my messages, so I don't have to do it myself."
        var listTwitMessage = twitSplit.splitMessage(message)
        assertEquals("1/2 I can't believe Tweeter now supports chunking", listTwitMessage[0])
        assertEquals("2/2 my messages, so I don't have to do it myself.", listTwitMessage[1])

        message = "I can't believe Tweeter now supports chunking"
        listTwitMessage = twitSplit.splitMessage(message)
        assertEquals("I can't believe Tweeter now supports chunking", listTwitMessage[0])
    }
}