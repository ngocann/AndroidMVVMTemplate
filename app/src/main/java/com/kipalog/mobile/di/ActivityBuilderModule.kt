package com.kipalog.mobile.di

import android.arch.lifecycle.ViewModel
import com.kipalog.mobile.ui.PostDetailActivity
import com.kipalog.mobile.ui.home.HomeActivity
import com.kipalog.mobile.ui.main.AddBoardActivity
import com.kipalog.mobile.ui.main.MainActivity
import com.kipalog.mobile.ui.main.RelayActivity
import com.kipalog.mobile.viewmodel.AddBoardViewModel
import com.kipalog.mobile.viewmodel.BoardViewModel
import com.kipalog.mobile.viewmodel.HomeViewModel
import com.kipalog.mobile.viewmodel.RelayViewModel
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap
import elcom.efarm.supervisor.di.ViewModelKey

@Module
internal abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun mainActivity(): MainActivity

    @Binds
    @IntoMap
    @ViewModelKey(BoardViewModel::class)
    abstract fun bindTwitViewModel(viewModel: BoardViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun addTwitActivity(): AddBoardActivity
    @Binds
    @IntoMap
    @ViewModelKey(AddBoardViewModel::class)
    abstract fun bindAddBoardViewModel(viewModel: AddBoardViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun relayActivity(): RelayActivity
    @Binds
    @IntoMap
    @ViewModelKey(RelayViewModel::class)
    abstract fun bindRelayViewModel(viewModel: RelayViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel

    @ContributesAndroidInjector
    internal abstract fun ostDetailActivity(): PostDetailActivity
}
