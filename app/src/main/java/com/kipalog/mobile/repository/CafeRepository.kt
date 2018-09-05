package com.kipalog.mobile.repository

import com.kipalog.mobile.api.CafeServices
import com.kipalog.mobile.model.Cafe
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CafeRepository @Inject constructor(val cafeServices: CafeServices) {

    fun getCafe() : Single<List<Cafe>> {
        return cafeServices.getCafe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

}