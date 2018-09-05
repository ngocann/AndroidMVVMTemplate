package com.kipalog.mobile.ui

import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.cafeDetail.CafeDetailActivity
import com.kipalog.mobile.ui.home.CafeActivity
import com.kipalog.mobile.ui.home.CafeHomeActivity2

import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            CafeActivity.start(this)
        }, 250)

    }

}
