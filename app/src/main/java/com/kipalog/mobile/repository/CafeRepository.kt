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
    fun getCafe(category: String? = null) : Single<List<Cafe>> {
        return cafeServices.getCafe()
                .map {
                    it.forEach { it.fav = cafeBookmarkList[it.id!!] != null }
                    if (category == null) return@map it else return@map filterCategory(it, category)
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

    fun filterCategory(cafeList: List<Cafe>?, category: String): ArrayList<Cafe> {
        val cafeListCategory = ArrayList<Cafe>()
        cafeList?.forEach {  if ( it.category == category ) cafeListCategory.add(it)}
        return cafeListCategory
    }

}