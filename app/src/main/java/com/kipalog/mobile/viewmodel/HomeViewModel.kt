package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.kipalog.mobile.model.Post
import com.kipalog.mobile.repository.PostRepository
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val postRepository: PostRepository) : BaseViewModel() {

    val newestLiveData : MutableLiveData<List<Post>> = MutableLiveData()
    val hotLiveData : MutableLiveData<List<Post>> = MutableLiveData()

    fun loadPostNewest() {
        getPostNewest()
    }
    fun loadPostHot() {
        getPostHot()
    }

    private fun getPostHot() {
        isLoading.value = true
        postRepository.getNewest().subscribe({
            newestLiveData.value = it
            isLoading.value = false
        }, {
            isLoading.value = false
            handleError(it)
        })
    }
    private fun getPostNewest() {
        isLoading.value = true
        postRepository.getNewest().subscribe({
            newestLiveData.value = it
            isLoading.value = false
        }, {
            isLoading.value = false
            handleError(it)
        })
    }
}