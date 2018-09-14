package com.kipalog.mobile.viewmodel

import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.repository.CafeRepository
import javax.inject.Inject

class HomeCafeViewModel @Inject constructor(private val cafeRepository: CafeRepository) : BaseViewModel() {

    private var cafeList: List<Cafe>? = null

    init {
        getCafe()
    }
    private fun getCafe() {
        cafeRepository.getCafe()
                .subscribe({ cafeList = it }, { handleError(it) })
    }

    fun getCafeByCategory(category : String) : List<Cafe> = cafeRepository.filterCategory(cafeList, category)

}