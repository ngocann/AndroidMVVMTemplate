package com.kipalog.mobile.repository

import com.kipalog.mobile.api.KipalogApi
import com.kipalog.mobile.db.PostDao
import com.kipalog.mobile.model.Post
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class PostRepository @Inject constructor(private val kipalogApi: KipalogApi, private val postDao: PostDao) {

    private fun saveHot(postList: List<Post>) {
        postDao.insert(postList)
    }

    fun getPost(id : String): Flowable<Post> {
        return postDao.getPost(id)
    }

    fun getNewest() : Observable<List<Post>> {
        return kipalogApi.getNewest()
                .map { it.content }
                .map {
                    it.forEach { post ->
                        post.content = post.content.substring(0, endIndex = 100)
                    }
                    return@map it
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun getHot() : Observable<List<Post>> {
        return kipalogApi.getHot()
                .map { it.content }
                .doOnNext {
                    it.forEach { it.simpleContent = "" }
                    saveHot(it)
                }
                .map {
                    it.forEach { it.content = it.content.substring(0, endIndex = 100) }
                    return@map it
                }
                .observeOn(AndroidSchedulers.mainThread())
    }

}