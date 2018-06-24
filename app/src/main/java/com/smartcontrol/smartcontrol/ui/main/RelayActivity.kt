package com.smartcontrol.smartcontrol.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.smartcontrol.smartcontrol.R
import com.smartcontrol.smartcontrol.adapter.RelayAdapter
import com.smartcontrol.smartcontrol.databinding.ActivityRelayBinding
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.ui.BaseActivity
import com.smartcontrol.smartcontrol.viewmodel.RelayViewModel
import org.parceler.Parcel
import org.parceler.Parcels
import javax.inject.Inject

class RelayActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val relayAdapter = RelayAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityRelayBinding = DataBindingUtil.setContentView(this, R.layout.activity_relay)
        binding.executePendingBindings()
        binding.rvRelay.layoutManager = GridLayoutManager(this, 3)
        binding.rvRelay.adapter = relayAdapter
        val relayViewModel = ViewModelProviders.of(this, viewModelFactory).get(RelayViewModel::class.java)
        if (!intent.hasExtra("BOARD")) {
            finish()
        }else {
            val board = Parcels.unwrap<Board>(intent.getParcelableExtra("BOARD"))
            relayViewModel.setBoard(board)
        }
    }

    companion object {
        fun start(context: Context, board : Board) {
            val intent = Intent(context, RelayActivity::class.java)
            intent.putExtra("BOARD", Parcels.wrap(board))
            context.startActivity(intent)
        }
    }

}
