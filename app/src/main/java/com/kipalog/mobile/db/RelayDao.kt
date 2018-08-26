package com.kipalog.mobile.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.kipalog.mobile.model.Board
import com.kipalog.mobile.model.Relay
import io.reactivex.Flowable

@Dao
interface RelayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(relay: Relay)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(relay: Relay)

    @Query("SELECT * FROM relay")
    fun getAll(): Flowable<List<Relay>>

}