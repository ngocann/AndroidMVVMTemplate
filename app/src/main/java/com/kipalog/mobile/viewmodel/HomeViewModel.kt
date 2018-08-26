package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.LiveDataReactiveStreams
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Post
import com.kipalog.mobile.repository.BoardRepository
import com.kipalog.mobile.repository.PostRepository
import com.kipalog.mobile.util.Log
import io.reactivex.functions.Consumer
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val postRepository: PostRepository) : BaseViewModel() {

    val newestLiveData : MutableLiveData<List<Post>> = MutableLiveData()
    val hotLiveData : MutableLiveData<List<Post>> = MutableLiveData()

    fun loadPostNewest() {
        getPostNewest()
    }
    fun loadPostHot() {
        getHotNewest()
    }

    private fun getHotNewest() {
        isLoading.value = true
        postRepository.getHot().subscribe({
            hotLiveData.value = it
            isLoading.value = false
        }, {
            isLoading.value = false
            handleError(it)
        })
    }
    private fun getPostNewest() {
        isLoading.value = true
        postRepository.getHot().subscribe({
            newestLiveData.value = it
            isLoading.value = false
        }, {
            isLoading.value = false
            handleError(it)
        })
    }
}