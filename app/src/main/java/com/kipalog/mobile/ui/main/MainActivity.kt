package com.kipalog.mobile.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kipalog.mobile.adapter.BoardAdapter
import com.kipalog.mobile.databinding.ActivityMainBinding
import com.kipalog.mobile.extension.dialog
import com.kipalog.mobile.ui.base.BaseActivity
import com.kipalog.mobile.viewmodel.BoardViewModel
import kotlinx.android.synthetic.main.activity_main.*
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.content.BroadcastReceiver
import android.view.View
import com.kipalog.mobile.R
import com.kipalog.mobile.extension.isConnected


class MainActivity : BaseActivity(), BoardAdapter.OnItemClickListener, BoardAdapter.OnItemLongClickListener {

    override fun onItemLongClick(position: Int): Boolean {
        dialog(arrayOf("Delete","Edit")) {
            when (it) {
                0 -> boardViewModel.deleteBoard(boardAdapter.getItem(position))
                1 -> AddBoardActivity.start(this, boardAdapter.getItem(position))
            }
        }
        return true
    }

    override fun onItemClick(position: Int) {
        RelayActivity.start(this, boardAdapter.getItem(position))
    }

    private val boardAdapter = BoardAdapter(arrayListOf(), this)

    private lateinit var boardViewModel: BoardViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.executePendingBindings()
        binding.rvBoard.layoutManager = GridLayoutManager(this, 3)
        binding.rvBoard.adapter = boardAdapter
        boardViewModel = ViewModelProviders.of(this, viewModelFactory).get(BoardViewModel::class.java)

        boardViewModel.getBoards().observe(this, Observer {
            it?.let {
                log("list board ${it.size}")
                boardAdapter.setNewData(it)
                boardViewModel.checkStatus()
            }
        })
        boardViewModel.getModelCheckStatus().observe(this, Observer {
            it?.let {
                swiperefresh.isRefreshing = false
            }
        })
        boardAdapter.onItemLongClickListener = this
        fabAdd.setOnClickListener { AddBoardActivity.start(this) }

        binding.swiperefresh.setOnRefreshListener {
            boardViewModel.checkStatus()
        }


    }

    override fun onStart() {
        super.onStart()
        registerInternetReceiver()
    }

    override fun onResume() {
        super.onResume()
        if (isConnected()) {
            tvNetwork.visibility = View.GONE
        }else {
            tvNetwork.visibility = View.VISIBLE
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterInternetReceiver()
    }

    private var internetReceiver : BroadcastReceiver? = null
    private fun registerInternetReceiver() {
        if (this.internetReceiver != null) return
        internetReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                log("onReceive ${intent.action}")
                if (context.isConnected()) {
                    boardViewModel.checkStatus()
                    tvNetwork.visibility = View.GONE
                }else {
                    tvNetwork.visibility = View.VISIBLE
                }
            }
        }
        val filter = IntentFilter()
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(internetReceiver, filter)
    }
    private fun unRegisterInternetReceiver() {
        internetReceiver?.let { unregisterReceiver(it) }
    }

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }


}
