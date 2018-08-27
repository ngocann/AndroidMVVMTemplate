package com.kipalog.mobile.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.kipalog.mobile.model.Post

@Database(
        entities = [
            Post::class
        ],
        version = 7,
        exportSchema = false)
abstract class KipalogDb : RoomDatabase() {
    abstract fun postDao(): PostDao
}