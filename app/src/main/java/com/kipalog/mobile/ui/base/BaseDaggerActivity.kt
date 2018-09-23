package com.kipalog.mobile.ui.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.kipalog.mobile.util.Log
import com.kipalog.mobile.viewmodel.BaseViewModel
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.DaggerAppCompatActivity
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

abstract class BaseDaggerActivity<T : BaseViewModel?> : BaseActivity(), HasSupportFragmentInjector, HasFragmentInjector {
    @Inject
    lateinit var supportFragmentInjector : DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var frameworkFragmentInjector : DispatchingAndroidInjector<android.app.Fragment>
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    var viewmodel: T? = null

    abstract fun classViewModel(): Class<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProviders.of(this, viewModelFactory).get(classViewModel())
        viewmodel?.isLoading?.let { enableModelLoading(it) }
        viewmodel?.errorMessage?.let { enableModelMessage(it) }
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

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return supportFragmentInjector
    }

    override fun fragmentInjector(): AndroidInjector<android.app.Fragment> {
        return frameworkFragmentInjector
    }
}
