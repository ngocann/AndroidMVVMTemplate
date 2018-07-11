package com.smartcontrol.smartcontrol.db

import android.arch.persistence.room.*
import com.smartcontrol.smartcontrol.model.Board
import com.smartcontrol.smartcontrol.model.Twit
import io.reactivex.Flowable

@Dao
interface BoardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(board: Board)

    @Delete()
    fun delete(board: Board)

    @Update()
    fun update(board: Board)

    @Update()
    fun updateAll(boards: List<Board>)

    @Query("SELECT * FROM board")
    fun getAll(): Flowable<List<Board>>

}