package com.smartcontrol.smartcontrol.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.parceler.Parcel

@Parcel
@Entity(tableName = "board")
data class Board (@PrimaryKey(autoGenerate = true) var id: Long?,
                  var name : String?,
                  var host : String?,
                  var username : String?,
                  var password : String?){

//    constructor(
//            name : String?,
//            host : String?,
//            username : String?,
//            password : String?
//    ) : this(null, name, host, username, password)


}