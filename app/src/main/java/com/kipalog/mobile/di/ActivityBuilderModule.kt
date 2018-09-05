package com.kipalog.mobile.di

import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.ui.cafeDetail.CafeDetailActivity
import com.kipalog.mobile.ui.home.CafeActivity
import com.kipalog.mobile.ui.home.CafeHomeActivity2
import com.kipalog.mobile.viewmodel.CafeDetailViewModel
import com.kipalog.mobile.viewmodel.CafeViewModel
import com.kipalog.mobile.viewmodel.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import elcom.efarm.supervisor.di.ViewModelKey

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun cafeHomeActivity2(): CafeHomeActivity2

    @ContributesAndroidInjector
    internal abstract fun cafeActivity(): CafeActivity

    @ContributesAndroidInjector
    internal abstract fun cafeDetailActivity(): CafeDetailActivity

    @Binds
    @IntoMap
    @ViewModelKey(CafeViewModel::class)
    abstract fun bindCafeViewModel(viewModel: CafeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CafeDetailViewModel::class)
    abstract fun bindCafeDetailViewModel(viewModel: CafeDetailViewModel): ViewModel

}
