package com.kipalog.mobile.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.kipalog.mobile.viewmodel.BaseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open abstract class BaseFragment : Fragment() {

    var processBar : ProgressBar? = null

    abstract fun layoutId() : Int
    open fun initView(view : View) {
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId(), container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }

    fun toast (message : String) {
        Toast.makeText(context!!, message, Toast.LENGTH_LONG).show()
    }

    fun restartApp(intent: Intent ) {
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or
                Intent.FLAG_ACTIVITY_NEW_TASK or
                Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    fun enableModelMessage(modelMessage : MutableLiveData<String>) {
        modelMessage.observe(this, Observer { it?.let { showDialogMessage(it, null) } })
    }
    private fun showDialogMessage(message: String, callbacks : (() -> Unit)? ) {
        activity?.let {
            MaterialDialog.Builder(it)
                    .content(message)
                    .positiveText(android.R.string.ok)
                    .negativeText(android.R.string.cancel)
                    .onPositive { _, _ -> callbacks?.invoke() }
                    .build()
                    .show()
        }
    }

    fun enableModelLoading(modelLoadding : MutableLiveData<Boolean>) {
        if (processBar == null) {
            processBar = ProgressBar(activity)
            processBar?.isIndeterminate = true
        }
        modelLoadding.observe(this, Observer { it?.let { if (it) showProcessBar() else hideProcessBar() } })
    }

    open fun showProcessBar() {
        processBar?.visibility = View.VISIBLE
    }

    open fun hideProcessBar() {
        processBar?.visibility = View.GONE
    }

}