package com.smartcontrol.smartcontrol.di

import android.arch.lifecycle.ViewModel
import com.smartcontrol.smartcontrol.ui.main.AddBoardActivity
import com.smartcontrol.smartcontrol.ui.main.MainActivity
import com.smartcontrol.smartcontrol.ui.main.RelayActivity
import com.smartcontrol.smartcontrol.viewmodel.AddBoardViewModel
import com.smartcontrol.smartcontrol.viewmodel.MainViewModel
import com.smartcontrol.smartcontrol.viewmodel.RelayViewModel
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
    @ViewModelKey(MainViewModel::class)
    abstract fun bindTwitViewModel(viewModel: MainViewModel): ViewModel

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
}
