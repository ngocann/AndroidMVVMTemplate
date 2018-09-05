package com.kipalog.mobile.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.kipalog.mobile.util.Log
import com.kipalog.mobile.viewmodel.BaseViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<T : BaseViewModel?> : DaggerAppCompatActivity() {
    val TAG = javaClass.name
    var processBar: ProgressBar? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var viewmodel: T? = null

    abstract fun classViewModel(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(classViewModel())
        viewmodel?.isLoading?.let { enableModelLoading(it) }
        viewmodel?.errorMessage?.let { enableModelMessage(it) }

    }

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

    open fun enableModelMessage(modelMessage: MutableLiveData<String>) {
        modelMessage.observe(this, Observer { it?.let { showDialogMessage(it, null) } })
    }

    open fun enableModelLoading(modelLoadding: MutableLiveData<Boolean>) {
        modelLoadding.observe(this, Observer {
            it?.let {
                if (it) showProcessBar() else hideProcessBar()
            }
        })
    }

    fun enableModelToast(modelToast: MutableLiveData<String>) {
        modelToast.observe(this, Observer { it?.let { toast(it) } })
    }
}