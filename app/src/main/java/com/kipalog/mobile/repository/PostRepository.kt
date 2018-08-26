package com.kipalog.mobile.repository

import com.kipalog.mobile.api.KipalogApi
import com.kipalog.mobile.model.Post
import com.kipalog.mobile.model.PostResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostRepository @Inject constructor(val kipalogApi: KipalogApi) {

    fun getNewest() : Observable<List<Post>> {
        return kipalogApi.getNewest()
                .map { it.content }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHot() : Observable<List<Post>> {
        return kipalogApi.getHot()
                .map { it.content }
                .observeOn(AndroidSchedulers.mainThread())
    }

}