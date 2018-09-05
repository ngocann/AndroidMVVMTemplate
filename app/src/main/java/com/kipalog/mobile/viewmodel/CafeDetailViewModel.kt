package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.google.android.gms.maps.model.LatLng
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.repository.CafeRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class CafeDetailViewModel @Inject constructor(private val cafeRepository: CafeRepository) : BaseViewModel() {

    val cafeBinding : ObservableField<Cafe> = ObservableField()
    val modelCafe = MutableLiveData<Cafe>()
    val modelFacebook = MutableLiveData<String>()
    val modelInstagram = MutableLiveData<String>()
    val modelPhone = MutableLiveData<String>()
    val modelLatLng = MutableLiveData<LatLng>()
    var cafe : Cafe? = null

    fun initData(cafe: Cafe) {
        cafe?.let {
            this.cafe = it
            cafeBinding.set(it)
            modelCafe.value = it
            if (cafe?.lat != null && cafe?.lng != null) {
                modelLatLng.value = LatLng(cafe?.lat!!, cafe?.lng!!)
            }
        }
    }

    fun onFacebookClicked() {
        modelFacebook.value = modelCafe.value?.fb
    }
    fun onInstagramClicked() {
        modelInstagram.value = modelCafe.value?.insta
    }
    fun onPhoneClicked() {
        modelPhone.value = modelCafe.value?.phone
    }
}