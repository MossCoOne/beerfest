package com.example.beerhive.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Beer(var beerName: String?=null,
                var beerDescription: String? = null,
                var beerBrewerTips: String? = null,
                var beerImageUrl: String? = null,
                var beerBrewingTipsTitle: String? = null,
                var volume: Int,
                var volumeUnit: String? = null):Parcelable