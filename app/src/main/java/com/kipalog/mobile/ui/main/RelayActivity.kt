package com.kipalog.mobile.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.kipalog.mobile.R
import com.kipalog.mobile.adapter.RelayAdapter
import com.kipalog.mobile.databinding.ActivityRelayBinding
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.ui.base.BaseActivity
import com.kipalog.mobile.viewmodel.RelayViewModel
import kotlinx.android.synthetic.main.activity_add.*
import org.parceler.Parcels

class RelayActivity : BaseActivity(), RelayAdapter.OnItemClickListener {

    private val relayAdapter = RelayAdapter(arrayListOf(), this)

    private lateinit var relayViewModel: RelayViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityRelayBinding = DataBindingUtil.setContentView(this, R.layout.activity_relay)
        binding.executePendingBindings()
        binding.rvRelay.layoutManager = GridLayoutManager(this, 3)
        binding.rvRelay.adapter = relayAdapter
        toolbar.setNavigationOnClickListener { onBackPressed() }
        relayViewModel = ViewModelProviders.of(this, viewModelFactory).get(RelayViewModel::class.java)
        if (!intent.hasExtra("BOARD")) {
            finish()
        }else {
            val board = Parcels.unwrap<Board>(intent.getParcelableExtra("BOARD"))
            relayViewModel.setBoard(board)
        }

        relayViewModel.relayLiveData.observe(this, Observer{
            it?.let { relayAdapter.setNewData(it) }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        relayViewModel.destroy()
    }

    override fun onItemClick(position: Int) {
        relayViewModel.pressRelay(relayAdapter.getItem(position))
    }

    companion object {
        fun start(context: Context, board : Board) {
            val intent = Intent(context, RelayActivity::class.java)
            intent.putExtra("BOARD", Parcels.wrap(board))
            context.startActivity(intent)
        }
    }

}
