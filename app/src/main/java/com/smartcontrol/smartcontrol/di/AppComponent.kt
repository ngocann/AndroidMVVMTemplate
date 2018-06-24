package com.smartcontrol.smartcontrol.di

import com.smartcontrol.smartcontrol.MyApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import elcom.efarm.supervisor.di.ViewModelBuilder
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class,
    FragmentBuilderModule::class,
    ActivityBuilderModule::class,
    ViewModelBuilder::class,
    AppModule::class])
interface AppComponent : AndroidInjector<MyApplication> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<MyApplication>()

}