package com.kipalog.mobile.db

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

@Singleton
class PreferenceHelper @Inject constructor(val context : Context) {
    val pref : SharedPreferences = context.getSharedPreferences("packed", Context.MODE_PRIVATE)

    fun saveCafe(idCafes : HashMap<String, String>) {
        pref.edit().putString("bookmark", Gson().toJson(idCafes)).commit()
    }

    fun getCafeBookmark() : HashMap<String, String> {
        val stringBookmark : String? = pref.getString("bookmark", null)
        if (stringBookmark == null) {
            return HashMap()
        }
        val itemType = object : TypeToken<HashMap<String, String>>() {}.type
        return Gson().fromJson<HashMap<String, String>>(stringBookmark!!, itemType)
    }
}