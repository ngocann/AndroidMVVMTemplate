package com.smartcontrol.smartcontrol.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Relay
import com.smartcontrol.smartcontrol.model.Twit
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