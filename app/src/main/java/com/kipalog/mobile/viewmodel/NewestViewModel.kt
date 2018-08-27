package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.model.Post
import com.kipalog.mobile.repository.PostRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class NewestViewModel @Inject constructor(private val postRepository: PostRepository) : BaseViewModel() {
    val newestPostLiveData = MutableLiveData<List<Post>>()
        get() = field

    fun getNewestPost() {
        postRepository.getNewest().subscribe {
            newestPostLiveData.value = it
        }
    }


}