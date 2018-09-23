package com.kipalog.mobile.di

import android.arch.persistence.room.Room
import android.content.Context
import com.kipalog.mobile.MyApplication
import com.kipalog.mobile.api.KipalogApi
import com.kipalog.mobile.db.KipalogDb
import com.kipalog.mobile.db.PostDao
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

    @Singleton
    @Provides
    fun provideDb(app: MyApplication): KipalogDb {
        return Room .databaseBuilder(app, KipalogDb::class.java, "twitdb.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Singleton
    @Provides
    fun providePostDao(db: KipalogDb): PostDao {
        return db.postDao()
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