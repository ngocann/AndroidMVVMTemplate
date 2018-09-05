package com.kipalog.mobile.ui.home

import android.arch.lifecycle.Observer
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.PostAdapter
import com.kipalog.mobile.databinding.FragmentHotBinding
import com.kipalog.mobile.databinding.FragmentNewestBinding
import com.kipalog.mobile.ui.base.BaseDaggerFragment
import com.kipalog.mobile.viewmodel.HomeViewModel

class HotFragment : BaseDaggerFragment<HomeViewModel>(), PostAdapter.OnItemClickListener {
    override fun classViewModel(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun layoutId(): Int = R.layout.fragment_hot

    private  var postAdapter = PostAdapter(arrayListOf(), this)

    lateinit var swipeRefresh : SwipeRefreshLayout
    override fun initView(view : View) {
        super.initView(view)
        swipeRefresh = (binding as FragmentHotBinding).swipeRefresh
        swipeRefresh.setOnRefreshListener { viewmodel?.loadPostHot() }
        val rvPost = (binding as FragmentHotBinding).rvPost
        rvPost.layoutManager = LinearLayoutManager(activity)
        rvPost.adapter = postAdapter
        viewmodel?.loadPostHot()
        viewmodel?.hotLiveData?.observe(this, Observer {
            it?.let { postAdapter.setNewData(it) }
        })
    }

    override fun showProcessBar() {
        super.showProcessBar()
        swipeRefresh.isRefreshing = true
    }

    override fun hideProcessBar() {
        super.hideProcessBar()
        swipeRefresh.isRefreshing = false
    }

    override fun onItemClick(position: Int) {
//        activity?.let { PostDetailActivity.start(it, postAdapter.getItem(position).id) }
    }


}