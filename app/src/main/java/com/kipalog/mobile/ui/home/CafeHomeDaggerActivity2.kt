package com.kipalog.mobile.ui.home

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import com.kipalog.mobile.R
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.ui.base.BaseDaggerActivity
import com.kipalog.mobile.ui.base.ListFragmentPagerAdapter
import com.kipalog.mobile.ui.cafeDetail.CafeDetailFragment
import com.kipalog.mobile.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.activity_home_cafe2.*

class CafeHomeDaggerActivity2 : BaseDaggerActivity<CafeViewModel>() {
    override fun classViewModel(): Class<CafeViewModel> = CafeViewModel::class.java
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CafeHomeDaggerActivity2::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_cafe2)
        viewmodel?.getCafe()
        viewmodel?.cafeLiveData?.observe(this, Observer {
            it?.let {
                initViewpager(it)
            }
        })
    }

    private fun initViewpager(it: List<Cafe>) {
        val listFragment = ArrayList<Fragment>();
        it.forEach { listFragment.add(CafeDetailFragment.newInstance(it)) }
        val adapter = ListFragmentPagerAdapter(supportFragmentManager, listFragment)
        viewPager.adapter = adapter
    }
}