package com.smartcontrol.smartcontrol.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.smartcontrol.smartcontrol.util.Log
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {
    val TAG = javaClass.name
    var processBar : ViewGroup? = null

    fun log(msg : String) {
        Log.d(TAG, msg)
    }

    fun showDialogMessage(message: String, callbacks : (() -> Unit)? ) {
        MaterialDialog.Builder(this)
                .content(message)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .onPositive { _, _ -> callbacks?.invoke() }
                .build()
                .show()
    }

    fun toast (message : String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun showProcessBar() {
        processBar?.visibility = View.VISIBLE
    }

    fun hideProcessBar() {
        processBar?.visibility = View.GONE
    }

    fun enableModelMessage(modelMessage : MutableLiveData<String>) {
        modelMessage.observe(this, Observer { it?.let { showDialogMessage(it, null) } })
    }

    fun enableModelLoading(modelLoadding : MutableLiveData<Boolean>) {
        modelLoadding.observe(this, Observer { it?.let {
            if (it) {
                showProcessBar()
            }else {
                hideProcessBar()
            }
        } })
    }

    fun enableModelToast(modelToast : MutableLiveData<String>) {
        modelToast.observe(this, Observer { it?.let { toast(it) } })
    }
}