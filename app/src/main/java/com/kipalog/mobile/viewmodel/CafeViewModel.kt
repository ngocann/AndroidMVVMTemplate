package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.repository.CafeRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class CafeViewModel @Inject constructor(private val cafeRepository: CafeRepository) : BaseViewModel() {

    val cafeLiveData : MutableLiveData<List<Cafe>> = MutableLiveData()
    var category : String? = null


    fun getCafe(category : String? = null) {
        isLoading.value = true
        cafeRepository.getCafe(category).subscribe({
            isLoading.value = false
            cafeLiveData.value = it
            it.forEach { cafe -> Log.d(cafe.toString()) }
        } ,{
            isLoading.value = false
            handleError(it)
        })
    }
}