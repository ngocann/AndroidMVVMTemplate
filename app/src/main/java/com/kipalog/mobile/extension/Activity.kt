package com.kipalog.mobile.extension

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build

fun Activity.hasPermission(permission: String) : Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
}

fun Activity.requestPermissionsSafely(permissions: Array<String>, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        requestPermissions(permissions, requestCode)
    }
}


