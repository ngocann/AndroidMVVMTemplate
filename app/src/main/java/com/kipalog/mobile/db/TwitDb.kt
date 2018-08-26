package com.kipalog.mobile.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay

@Database(
        entities = [
            Board::class, Relay::class],
        version = 5,
        exportSchema = false)
abstract class TwitDb : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    abstract fun relayDao(): RelayDao
}