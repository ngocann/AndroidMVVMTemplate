package com.kipalog.mobile.ui.home

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.PostAdapter
import com.kipalog.mobile.databinding.FragmentNewestBinding
import com.kipalog.mobile.ui.base.BaseDaggerFragment
import com.kipalog.mobile.ui.PostDetailActivity
import com.kipalog.mobile.viewmodel.HomeViewModel

class NewestFragment : BaseDaggerFragment<HomeViewModel>(), PostAdapter.OnItemClickListener {
    override fun classViewModel(): Class<HomeViewModel> = HomeViewModel::class.java
    override fun layoutId(): Int = R.layout.fragment_newest

    private  var postAdapter = PostAdapter(arrayListOf(), this)

    override fun initView() {
        super.initView()
        val rvPost = (binding as FragmentNewestBinding).rvPost
        rvPost.layoutManager = LinearLayoutManager(activity)
        rvPost.adapter = postAdapter
        viewmodel!!.loadPostNewest()
        viewmodel!!.newestLiveData.observe(this, Observer {
            it?.let { postAdapter.setNewData(it) }
        })

    }

    override fun onItemClick(position: Int) {
        activity?.let { PostDetailActivity.start(it, postAdapter.getItem(position).content) }
    }


}