package com.kipalog.mobile.api

import com.kipalog.mobile.api.firebase.FIRFirestore
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.util.Log
import io.reactivex.Single
import javax.inject.Inject

class CafeServices @Inject constructor(val firFirestore: FIRFirestore) {

    private val COLLECTION = "coffee"

    fun getCafe() : Single<List<Cafe>>{
        return firFirestore.read(COLLECTION)
                .flatMap {
                    val listCoffee = ArrayList<Cafe>()
                    it.result.forEach {
                        val coffee = Cafe()
                        coffee.address = it["address"] as String?
                        coffee.name = it["name"] as String?
                        coffee.category = it["category"] as String?
                        coffee.desc = it["desc"] as String?
                        coffee.district = it["district"] as String?
                        coffee.fb = it["fb"] as String?
                        coffee.hours = it["hours"] as String?
                        coffee.insta = it["insta"] as String?
                        coffee.map = it["map"] as String?
                        coffee.title = it["title"] as String?
                        coffee.phone = it["phone"] as String?
                        coffee.lat = it["lat"] as Double?
                        coffee.lng = it["lng"] as Double?
                        coffee.images = it["images"] as List<String>?
                        listCoffee.add(coffee)
                    }
                    return@flatMap Single.just(listCoffee)

        }
    }
}