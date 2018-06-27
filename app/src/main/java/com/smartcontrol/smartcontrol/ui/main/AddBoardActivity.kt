package com.smartcontrol.smartcontrol.ui.main

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.smartcontrol.smartcontrol.R
import com.smartcontrol.smartcontrol.databinding.ActivityAddBinding
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.ui.BaseActivity
import com.smartcontrol.smartcontrol.viewmodel.AddBoardViewModel
import kotlinx.android.synthetic.main.activity_add.*
import org.parceler.Parcels
import javax.inject.Inject

class AddBoardActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    companion object {
        fun start(context: Context, board: Board? = null){
            val intent = Intent(context, AddBoardActivity::class.java)
            board?.let {
                intent.putExtra("BOARD", Parcels.wrap(it))
            }
            context.startActivity(intent)
        }
    }

    private lateinit var addBoardViewModel: AddBoardViewModel
    private var board : Board? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityAddBinding = DataBindingUtil.setContentView(this, R.layout.activity_add)
        binding.executePendingBindings()
        toolbar.setNavigationOnClickListener { onBackPressed() }
        addBoardViewModel = ViewModelProviders.of(this, viewModelFactory).get(AddBoardViewModel::class.java)
        enableModelMessage(addBoardViewModel.getMessageLiveData())

        ivSave.setOnClickListener {
            addBoardViewModel.saveBoard(edtHost.text, edtUsername.text, edtPassword.text, edtName.text) {
                toast("Thêm thiết bị thành công!")
                onBackPressed()
            }
        }

        if (intent.hasExtra("BOARD")) {
            board = Parcels.unwrap<Board>(intent.getParcelableExtra("BOARD"))
            addBoardViewModel.board = board
        }

        if (board != null) {
            edtHost.setText(board?.host)
            edtUsername.setText(board?.username)
            edtPassword.setText(board?.password)
            edtName.setText(board?.name)
        }


    }



}