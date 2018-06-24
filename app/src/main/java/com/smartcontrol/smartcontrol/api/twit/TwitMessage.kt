package com.smartcontrol.smartcontrol.api.twit

import java.util.ArrayList

class TwitMessage(var index : Int, var total : Int) {

    val MAX_MESSAGE = 50
    var wordList : ArrayList<String> = ArrayList()

    constructor(index : Int, total : Int, word : String) : this(index, total) {
        wordList.add(word)
    }
    /**
     * return True: can add new word
     *        False : can not add, break new message
     */
    fun checkLargerAndAdd(word : String) : Boolean{
        return if (!isLargeLimit(word)) {
            false
        } else {
            wordList.add(word)
            true
        }
    }

    /**
     * return True: can add new word
     *        False : can not add, break new message
     */
    fun isLargeLimit(word : String) : Boolean {
        val length = wordList.joinToString(" ","$index/$total ").length
        val newLength = length + word.length
        return newLength <= MAX_MESSAGE
    }

    override fun toString(): String {
        return wordList.joinToString(" ","$index/$total ")
    }

}