package com.kipalog.mobile.ui.home

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.HomeCafeAdapter
import com.kipalog.mobile.adapter.SimpleBaseViewHolder
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.ui.base.BaseActivity
import com.kipalog.mobile.ui.cafeDetail.CafeDetailActivity
import com.kipalog.mobile.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.home_activity.*

class CafeActivity : BaseActivity<CafeViewModel>() {
    override fun classViewModel(): Class<CafeViewModel> = CafeViewModel::class.java
    companion object {
        fun start(context: Context) {
            val intent = Intent(context, CafeActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        viewmodel?.getCafe()
        viewmodel?.cafeLiveData?.observe(this, Observer {
            it?.let {
                initRecyclerView(it)
            }
        })
    }

    private fun initRecyclerView(it: List<Cafe>) {
        val adapter = HomeCafeAdapter(it, object : SimpleBaseViewHolder.OnItemClickListener {
            override fun onItemClick(position: Int) {
                showDetail(it[position])
            }
        })
        rv.adapter = adapter
    }

    private fun showDetail(cafe: Cafe) = CafeDetailActivity.start(this, cafe)
}