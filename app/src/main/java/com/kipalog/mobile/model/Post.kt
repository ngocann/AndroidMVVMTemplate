package com.kipalog.mobile.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.parceler.Parcel

@Entity(tableName = "post")
data class Post constructor(@PrimaryKey var id : String, var title : String, var content : String, var simpleContent : String = "") {
}