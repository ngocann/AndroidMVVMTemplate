package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField

open class BaseViewModel : ViewModel() {
    var isLoading = MutableLiveData<Boolean>()
    var errorMessage = MutableLiveData<String>()
    var toast = MutableLiveData<String>()

    fun handleError(throwable: Throwable) {
        isLoading.value = false
        throwable.message?.let { errorMessage.value = it }
    }
}