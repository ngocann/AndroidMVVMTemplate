package com.kipalog.mobile.api

import com.kipalog.mobile.BuildConfig
import com.kipalog.mobile.model.PostResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface KipalogApi {
    //KIPALOG_APIKEY="vhliziqt2m7s4f6zl93gk3i1u2r6ad"
    companion object {
        val BASE_URL: String
            get() = "https://kipalog.com"
    }

    @GET("/api/v1/post/newest")
    fun getNewest() : Observable<PostResponse>

    @GET("/api/v1/post/hot")
    fun getHot() : Observable<PostResponse>
}