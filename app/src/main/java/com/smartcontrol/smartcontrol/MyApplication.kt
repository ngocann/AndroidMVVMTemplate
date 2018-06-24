package com.smartcontrol.smartcontrol

import com.smartcontrol.smartcontrol.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyApplication : DaggerApplication()  {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().create(this)
    }
}