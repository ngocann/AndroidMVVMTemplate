package com.smartcontrol.smartcontrol.di

import android.app.Application
import android.arch.persistence.room.Room
import android.content.Context
import com.smartcontrol.smartcontrol.MyApplication
import com.smartcontrol.smartcontrol.db.BoardDao
import com.smartcontrol.smartcontrol.db.RelayDao
import com.smartcontrol.smartcontrol.db.TwitDao
import com.smartcontrol.smartcontrol.db.TwitDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun providesContext(application: MyApplication): Context {
        return application.applicationContext
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
    fun provideTwitDao(db: TwitDb): TwitDao {
        return db.twitDao()
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
}