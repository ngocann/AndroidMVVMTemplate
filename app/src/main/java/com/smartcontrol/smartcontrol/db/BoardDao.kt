package com.smartcontrol.smartcontrol.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import io.reactivex.Flowable

@Dao
interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(board: Board)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(board: Board)

    @Query("SELECT * FROM board")
    fun getAll(): Flowable<List<Board>>

}