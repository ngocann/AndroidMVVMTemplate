package com.kipalog.mobile.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.google.android.gms.maps.model.LatLng
import com.kipalog.mobile.R
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.repository.CafeRepository
import com.kipalog.mobile.util.Log
import javax.inject.Inject

class CafeDetailViewModel @Inject constructor(private val cafeRepository: CafeRepository) : BaseViewModel() {

    val cafeBinding: ObservableField<Cafe> = ObservableField()
    val modelCafe = MutableLiveData<Cafe>()
    val modelFacebook = MutableLiveData<String>()
    val modelInstagram = MutableLiveData<String>()
    val modelPhone = MutableLiveData<String>()
    val modelLatLng = MutableLiveData<LatLng>()
    var cafe: Cafe? = null

    fun initData(cafe: Cafe) {
        cafe?.let {
            cafeRepository.checkCafeFav(it)
            this.cafe = it
            modelCafe.value = it
            updateBinding(it)
            if (it?.lat != null && it?.lng != null) {
                modelLatLng.value = LatLng(it?.lat!!, it?.lng!!)
            }
        }
    }

    fun saveCafe() {
        cafe?.let {
            if (it.fav!!) {
                it.fav = false
                cafeRepository.unSaveCafe(it)
            } else {
                it.fav = true
                cafeRepository.saveCafe(it)
            }
            cafeBinding.set(it)
            cafeBinding.notifyChange()
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

    private fun updateBinding(cafe: Cafe) {
        cafeBinding.set(cafe)
    }

}