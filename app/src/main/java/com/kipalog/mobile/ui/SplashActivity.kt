package com.kipalog.mobile.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.home.HomeActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().postDelayed({
            HomeActivity.start(this)
        }, 250)

    }

}
