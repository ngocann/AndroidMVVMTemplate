package com.kipalog.mobile.di

import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.ui.cafeDetail.CafeDetailDaggerActivity
import com.kipalog.mobile.ui.cafe.CafeActivity
import com.kipalog.mobile.ui.home.CafeHomeDaggerActivity2
import com.kipalog.mobile.ui.home.HomeActivity
import com.kipalog.mobile.viewmodel.CafeDetailViewModel
import com.kipalog.mobile.viewmodel.CafeViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import elcom.efarm.supervisor.di.ViewModelKey

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun cafeHomeActivity2(): CafeHomeDaggerActivity2

    @ContributesAndroidInjector
    internal abstract fun cafeActivity(): CafeActivity

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun cafeDetailActivity(): CafeDetailDaggerActivity

    @Binds
    @IntoMap
    @ViewModelKey(CafeViewModel::class)
    abstract fun bindCafeViewModel(viewModel: CafeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CafeDetailViewModel::class)
    abstract fun bindCafeDetailViewModel(viewModel: CafeDetailViewModel): ViewModel

}
