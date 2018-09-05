package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.repository.CafeRepository
import com.kipalog.mobile.repository.PostRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class CafeViewModel @Inject constructor(private val postRepository: PostRepository, val cafeRepository: CafeRepository) : BaseViewModel() {

    val cafeLiveData : MutableLiveData<List<Cafe>> = MutableLiveData()
    fun getCafe() {
        isLoading.value = true
        cafeRepository.getCafe().subscribe({
            isLoading.value = false
            cafeLiveData.value = it
            it.forEach { cafe -> Log.d(cafe.toString()) }
        } ,{
            isLoading.value = false
            handleError(it)
        })
    }
}