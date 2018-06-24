package com.smartcontrol.smartcontrol.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import com.smartcontrol.smartcontrol.R
import com.smartcontrol.smartcontrol.adapter.BoardAdapter
import com.smartcontrol.smartcontrol.databinding.ActivityMainBinding
import com.smartcontrol.smartcontrol.ui.BaseActivity
import com.smartcontrol.smartcontrol.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val boardAdapter = BoardAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executePendingBindings()
        binding.rvBoard.layoutManager = GridLayoutManager(this, 3)
        binding.rvBoard.adapter = boardAdapter
        val twitViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        twitViewModel.getBoards().observe(this, Observer {
            it?.let {
                log("list twit ${it.size}")
                boardAdapter.setNewData(it)
            }
        })
        fabAdd.setOnClickListener { AddBoardActivity.start(this) }

    }


}
