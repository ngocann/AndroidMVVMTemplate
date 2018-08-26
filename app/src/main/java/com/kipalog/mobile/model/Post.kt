package com.kipalog.mobile.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

data class Post(val id : String, val title : String, val content : String) {
}