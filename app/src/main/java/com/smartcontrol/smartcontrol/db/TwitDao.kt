package com.smartcontrol.smartcontrol.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.smartcontrol.smartcontrol.model.Twit
import io.reactivex.Flowable

@Dao
interface TwitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(twit: Twit)

    @Query("SELECT * FROM twit")
    fun getAll(): Flowable<List<Twit>>

}