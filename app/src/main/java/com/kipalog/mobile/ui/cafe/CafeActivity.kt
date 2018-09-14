package com.kipalog.mobile.ui.cafe

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.HomeCafeAdapter
import com.kipalog.mobile.adapter.SimpleBaseViewHolder
import com.kipalog.mobile.model.Cafe
import com.kipalog.mobile.ui.base.BaseDaggerActivity
import com.kipalog.mobile.ui.cafeDetail.CafeDetailDaggerActivity
import com.kipalog.mobile.viewmodel.CafeViewModel
import kotlinx.android.synthetic.main.home_activity.*
import org.parceler.Parcels

class CafeActivity : BaseDaggerActivity<CafeViewModel>() {
    override fun classViewModel(): Class<CafeViewModel> = CafeViewModel::class.java
    companion object {
        const val BUNDLE_LIST_CAFE = "BUNDLE_LIST_CAFE"
        fun start(context: Context, category : String? = null, cafeList: List<Cafe>) {
            val intent = Intent(context, CafeActivity::class.java)
            intent.putExtra(BUNDLE_LIST_CAFE, Parcels.wrap(cafeList))
            context.startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
//        val category = intent.getStringExtra(BUNDLE_LIST_CAFE)
        val listCafe : List<Cafe> = Parcels.unwrap(intent.getParcelableExtra(BUNDLE_LIST_CAFE))
        viewmodel?.cafeLiveData?.value = listCafe
//        viewmodel?.getCafe(category)
        viewmodel?.cafeLiveData?.observe(this, Observer {
            it?.let { initRecyclerView(it) }
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
    private fun showDetail(cafe: Cafe) = CafeDetailDaggerActivity.start(this, cafe)

}