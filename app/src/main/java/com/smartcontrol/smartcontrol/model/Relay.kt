package com.smartcontrol.smartcontrol.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "relay")
data class Relay (@PrimaryKey(autoGenerate = true) var id: Long?,
                  val idBoard : String?,
                  val port : Int?,
                  val name : String?,
                  val host : String?,
                  val username : String?,
                  val password : String?){
}