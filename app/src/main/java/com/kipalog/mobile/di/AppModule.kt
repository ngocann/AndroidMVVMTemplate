package com.kipalog.mobile.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.kipalog.mobile.MyApplication
import com.kipalog.mobile.api.KipalogApi
import com.kipalog.mobile.api.KipalogApi.Companion.BASE_URL
import com.kipalog.mobile.db.BoardDao
import com.kipalog.mobile.db.RelayDao
import com.kipalog.mobile.db.TwitDb
import com.kipalog.mobile.helper.JSoupHelper
import com.kipalog.mobile.util.OkHttpClient2
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providesContext(application: MyApplication): Context {
        return application.applicationContext
    }

    @Provides
    fun providesJsoupHelper() : JSoupHelper {
        return JSoupHelper()
    }
    @Singleton
    @Provides
    fun provideDb(app: MyApplication): TwitDb {
        return Room .databaseBuilder(app, TwitDb::class.java, "twitdb.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun provideBoardDao(db: TwitDb): BoardDao {
        return db.boardDao()
    }

    @Singleton
    @Provides
    fun provideRelayDao(db: TwitDb): RelayDao {
        return db.relayDao()
    }

    @Singleton
    @Provides
    fun provideKialogApi(retrofit: Retrofit) : KipalogApi {
        return retrofit.create(KipalogApi::class.java)
    }

    @Singleton
    @Provides
    internal fun provideRetrofitInterface(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(KipalogApi.BASE_URL)
                .client(OkHttpClient2.getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))

                .build()
    }
}