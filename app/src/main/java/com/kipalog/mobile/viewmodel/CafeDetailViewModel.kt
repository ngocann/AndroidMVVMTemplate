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

    val cafeBinding : ObservableField<Cafe> = ObservableField()
    val categoryColorCafeBinding : ObservableField<Int> = ObservableField(R.color.vc)
    val categoryTextColorCafeBinding : ObservableField<Int> = ObservableField(R.color.text_vc)
    val modelCafe = MutableLiveData<Cafe>()
    val modelFacebook = MutableLiveData<String>()
    val modelInstagram = MutableLiveData<String>()
    val modelPhone = MutableLiveData<String>()
    val modelLatLng = MutableLiveData<LatLng>()
    var cafe : Cafe? = null

    fun initData(cafe: Cafe) {
        cafe?.let {
            this.cafe = it
            modelCafe.value = cafe
            updateBinding(it)
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

    private fun updateBinding(cafe : Cafe) {
        cafeBinding.set(cafe)
        categoryColorCafeBinding.set(cafe.category?.let { getColorFromCategory(it) })
        categoryTextColorCafeBinding.set(cafe.category?.let { getTextColorFromCategory(it) })
    }
    private fun getColorFromCategory(category: String) : Int {
        return when(category) {
            "VC" ->  R.color.vc
            "SD" ->  R.color.sd
            "BYT" ->  R.color.byt
            "EF" ->  R.color.ef
            else -> R.color.vc
        }
    }
    private fun getTextColorFromCategory(category: String) : Int {
        return when(category) {
            "VC" ->  R.color.text_vc
            "SD" ->  R.color.text_sd
            "BYT" ->  R.color.text_byt
            "EF" ->  R.color.text_ef
            else -> R.color.text_vc
        }
    }


}