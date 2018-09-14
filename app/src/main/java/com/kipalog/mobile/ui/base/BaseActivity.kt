package com.kipalog.mobile.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.kipalog.mobile.util.Log

abstract class BaseActivity : AppCompatActivity() {
    val TAG = javaClass.name
    var processBar: ProgressBar? = null

    fun log(msg: String) {
        Log.d(TAG, msg)
    }
    open fun showDialogMessage(message: String, callbacks: (() -> Unit)?) {
        MaterialDialog.Builder(this)
                .content(message)
                .positiveText(android.R.string.ok)
                .negativeText(android.R.string.cancel)
                .onPositive { _, _ -> callbacks?.invoke() }
                .build()
                .show()
    }

    open fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    open fun showProcessBar() {
        if (processBar == null) {
            processBar = ProgressBar(this)
            processBar?.isIndeterminate = true
        }
        processBar?.visibility = View.VISIBLE
    }

    open fun hideProcessBar() {
        processBar?.visibility = View.GONE
    }
}