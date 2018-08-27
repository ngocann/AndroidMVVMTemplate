package com.kipalog.mobile.db

import android.arch.persistence.room.*
import com.kipalog.mobile.model.Post
import io.reactivex.Flowable

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(posts: List<Post>)

    @Delete()
    fun delete(post: Post)

    @Update()
    fun update(post: Post)

    @Update()
    fun updateAll(posts: List<Post>)

    @Query("SELECT * FROM post")
    fun getAll(): Flowable<List<Post>>

    @Query("SELECT * FROM post WHERE id == :id")
    fun getPost(id : String): Flowable<Post>

}