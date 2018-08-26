package com.kipalog.mobile.di

import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.ui.home.HotFragment
import com.kipalog.mobile.ui.home.NewestFragment
import com.kipalog.mobile.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import elcom.efarm.supervisor.di.ViewModelKey

@Module
internal abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun newestFragment(): NewestFragment

    @ContributesAndroidInjector
    internal abstract fun hotFragment(): HotFragment
}