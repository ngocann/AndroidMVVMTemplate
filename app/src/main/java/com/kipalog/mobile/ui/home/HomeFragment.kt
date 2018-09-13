package com.kipalog.mobile.ui.home

import android.view.View
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.base.BaseDaggerFragment
import com.kipalog.mobile.viewmodel.HomeCafeViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseDaggerFragment<HomeCafeViewModel>() {
    override fun layoutId(): Int = R.layout.fragment_home
    override fun classViewModel(): Class<HomeCafeViewModel> = HomeCafeViewModel::class.java
    override fun initView(view: View) {
        super.initView(view)
        tvCategoryVc.setOnClickListener {

        }
    }
}