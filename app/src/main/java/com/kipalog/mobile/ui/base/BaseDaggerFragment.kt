package com.kipalog.mobile.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.kipalog.mobile.BR
import com.kipalog.mobile.R
import com.kipalog.mobile.viewmodel.BaseViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

open abstract class BaseDaggerFragment<T : BaseViewModel> : DaggerFragment() {

    var processBar : ProgressBar? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var binding: ViewDataBinding
    var viewmodel : T? = null

    abstract fun layoutId() : Int
    abstract fun classViewModel() : Class<T>
    open fun progressBarId() : Int {
        return -1
    }
    open fun initView(view : View) {
        processBar = view.findViewById(progressBarId())
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId(), container, false)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(classViewModel())
        binding.setVariable(BR.viewModel, viewmodel)
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel?.isLoading?.let { enableModelLoading(it) }
        viewmodel?.errorMessage?.let { enableModelMessage(it) }
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
        modelLoadding.observe(this, Observer { it?.let { if (it) showProcessBar() else hideProcessBar() } })
    }

    open fun showProcessBar() {
        processBar?.visibility = View.VISIBLE
    }

    open fun hideProcessBar() {
        processBar?.visibility = View.GONE
    }

}