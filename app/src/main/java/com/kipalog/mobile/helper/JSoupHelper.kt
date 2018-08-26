package com.kipalog.mobile.helper

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class JSoupHelper {

    fun parse(html : String) : Document {
        return Jsoup.parse(html)
    }

    fun parse(html: String, selector : String) : Elements {
        val document = parse(html)
        return document.select(selector)
    }
}