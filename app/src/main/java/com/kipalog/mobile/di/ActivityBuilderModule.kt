package com.kipalog.mobile.di

import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.ui.PostDetailActivity
import com.kipalog.mobile.ui.home.HomeActivity
import com.kipalog.mobile.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import elcom.efarm.supervisor.di.ViewModelKey

@Module
internal abstract class ActivityBuilderModule {


    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun ostDetailActivity(): PostDetailActivity
}
