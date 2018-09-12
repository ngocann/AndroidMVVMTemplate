package com.kipalog.mobile.extension

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.support.annotation.RequiresPermission
import android.content.Intent
import android.net.Uri
import com.google.android.gms.common.util.ClientLibraryUtils.getPackageInfo
import android.support.v4.content.ContextCompat.startActivity
import android.content.ActivityNotFoundException
import android.support.v4.content.ContextCompat.startActivity

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

@SuppressLint("MissingPermission")
fun Context.openCaller(phone : String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("tel:$phone"))
    startActivity(intent)
}

fun Context.openFacebook(page : String) {
    var intent : Intent = try {
        packageManager.getPackageInfo("com.facebook.katana", 0)
        Intent(Intent.ACTION_VIEW, Uri.parse("fb://facewebmodal/f?href=555759494587669"))
    } catch (e: Exception) {
        Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/$page"))
    }
    startActivity(intent)
}

fun Context.openInstagram(profile : String) {
    val uri = Uri.parse("http://instagram.com/_u/$profile")
    val likeIng = Intent(Intent.ACTION_VIEW, uri)
    likeIng.setPackage("com.instagram.android")
    try {
        startActivity(likeIng)
    } catch (e: ActivityNotFoundException) {
        startActivity(Intent(Intent.ACTION_VIEW,
                Uri.parse("http://instagram.com/$profile")))
    }

}

