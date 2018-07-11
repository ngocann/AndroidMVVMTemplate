package com.smartcontrol.smartcontrol.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit

@Database(
        entities = [
            Board::class, Relay::class],
        version = 5,
        exportSchema = false)
abstract class TwitDb : RoomDatabase() {
    abstract fun boardDao(): BoardDao
    abstract fun relayDao(): RelayDao
}