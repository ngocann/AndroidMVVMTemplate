package com.kipalog.mobile.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.parceler.Parcel

@Parcel
data class Cafe constructor(var id: String? = null,
                            var name: String? = null,
                            var address: String? = null,
                            var category: String? = null,
                            var desc: String? = null,
                            var district: String? = null,
                            var fb: String? = null,
                            var hours: String? = null,
                            var insta: String? = null,
                            var map: String? = null,
                            var title: String? = null,
                            var phone: String? = null,
                            var fav: Boolean? = false,
                            var lat: Double? = null,
                            var lng: Double? = null,
                            var images: List<String>? = null
)