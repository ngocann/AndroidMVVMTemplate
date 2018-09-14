package com.kipalog.mobile.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.base.BaseActivity
import com.kipalog.mobile.ui.base.BaseDaggerActivity
import com.kipalog.mobile.viewmodel.HomeCafeViewModel

class HomeActivity : BaseDaggerActivity<HomeCafeViewModel>() {
    override fun classViewModel(): Class<HomeCafeViewModel> = HomeCafeViewModel::class.java

    companion object {
        fun start(context: Context ) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}