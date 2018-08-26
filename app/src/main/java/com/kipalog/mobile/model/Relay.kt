package com.kipalog.mobile.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.jsoup.nodes.Element

@Entity(tableName = "relay")
data class Relay (@PrimaryKey(autoGenerate = true) var id: Long? = null,
                  var idBoard : Long?,
                  var port : String?,
                  var name : String?,
                  var led : String?,
                  var status : Boolean? = false
                  ){
    //<input type="button" class="buttonOn" value="G2  " onclick="newAJAXCommand('leds.cgi?led=2');" id="led2">

//    companion object {
//        fun objectFromElement(element : Element, idBoard: Long?) : Relay {
//            val name = element.attr("value")
//            val port = element.attr("onclick")
//           return Relay(null, idBoard, port, name)
//        }
//    }
}