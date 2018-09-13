package com.kipalog.mobile.util

import android.databinding.BindingAdapter
import android.databinding.InverseBindingAdapter
import android.text.Html
import android.text.TextUtils
import android.widget.ImageView
import android.widget.TextView
import com.kipalog.mobile.R

import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException

object BindingAdapterUtils {

    @BindingAdapter("categoryBackground")
    @JvmStatic
    fun setCategoryBackground(textView: TextView, category: String?) {
        if (category == null ){
            return
        }
        val colorBackground = when(category!!) {
            "VC" ->  R.color.vc
            "SD" ->  R.color.sd
            "BYT" ->  R.color.byt
            "EF" ->  R.color.ef
            else -> R.color.vc
        }
        val colorText = when(category!!) {
            "VC" ->  R.color.text_vc
            "SD" ->  R.color.text_sd
            "BYT" ->  R.color.text_byt
            "EF" ->  R.color.text_ef
            else -> R.color.text_vc
        }
        textView.setTextColor(textView.resources.getColor(colorText))
        textView.setBackgroundResource(colorBackground)
    }

}
