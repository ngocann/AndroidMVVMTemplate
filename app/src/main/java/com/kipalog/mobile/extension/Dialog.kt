package com.kipalog.mobile.extension

import android.app.Activity
import android.content.DialogInterface
import android.support.v7.app.AlertDialog

fun Activity.dialog(list: Array<String>, onclick : ((Int) -> Unit)) {
    val builder = AlertDialog.Builder(this)
            .setItems(list) { _: DialogInterface, i: Int ->
                onclick(i)
            }
      builder.show()
}
