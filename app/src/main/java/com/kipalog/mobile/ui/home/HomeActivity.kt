package com.kipalog.mobile.ui.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.ui.base.BaseActivity
import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : BaseActivity() {
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, HomeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val homePagerAdapter = HomePagerAdapter(supportFragmentManager)
        tabLayout.setupWithViewPager(viewpager)
        viewpager.adapter = homePagerAdapter

    }
}