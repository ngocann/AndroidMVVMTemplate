package com.smartcontrol.smartcontrol.extension

import android.Manifest
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission

@RequiresPermission(value = Manifest.permission.ACCESS_NETWORK_STATE)
fun Context.isConnected(): Boolean {
    val connectivityManager = this
            .getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    connectivityManager?.let {
        val netInfo = it.activeNetworkInfo
        netInfo?.let {
            if (it.isConnected) return true
        }
    }
    return false
}

