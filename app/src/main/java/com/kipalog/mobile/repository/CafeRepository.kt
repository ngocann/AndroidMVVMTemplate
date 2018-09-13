package com.kipalog.mobile.repository

import com.kipalog.mobile.api.CafeServices
import com.kipalog.mobile.db.PreferenceHelper
import com.kipalog.mobile.model.Cafe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CafeRepository @Inject constructor(private val cafeServices: CafeServices, private val preferenceHelper: PreferenceHelper) {

    var cafeBookmarkList : HashMap<String, String> = preferenceHelper.getCafeBookmark()
    fun getCafe() : Single<List<Cafe>> {
        return cafeServices.getCafe()
                .map {
                    it.forEach { it.fav = cafeBookmarkList[it.id!!] != null }
                    return@map it
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun checkCafeFav(cafe: Cafe) {
        cafeBookmarkList = preferenceHelper.getCafeBookmark()
        cafe.fav = cafeBookmarkList[cafe.id!!] != null
    }
    private fun saveBookmark() {
        preferenceHelper.saveCafe(cafeBookmarkList)
    }

    fun saveCafe(cafe: Cafe) {
        cafeBookmarkList[cafe.id!!] = cafe.id!!
        saveBookmark()
    }

    fun unSaveCafe(cafe: Cafe) {
        cafeBookmarkList.remove(cafe.id!!)
        saveBookmark()
    }

}