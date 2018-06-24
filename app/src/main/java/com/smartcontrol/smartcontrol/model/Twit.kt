package com.smartcontrol.smartcontrol.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "twit")
data class Twit (@PrimaryKey(autoGenerate = true) var id: Long?,
                 var message : String,
                 var dataTime : Long)

