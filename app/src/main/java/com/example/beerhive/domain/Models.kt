package com.example.beerhive.domain

import android.os.Parcel
import android.os.Parcelable

data class Beer(var beerName: String?=null,
                var beerDescription: String? = null,
                var beerBrewerTips: String? = null,
                var beerImageUrl: String? = null,
                var beerBrewingTipsTitle: String? = null,
                var volume: Int,
                var volumeUnit: String? = null):Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(beerName)
        parcel.writeString(beerDescription)
        parcel.writeString(beerBrewerTips)
        parcel.writeString(beerImageUrl)
        parcel.writeString(beerBrewingTipsTitle)
        parcel.writeInt(volume)
        parcel.writeString(volumeUnit)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Beer> {
        override fun createFromParcel(parcel: Parcel): Beer {
            return Beer(parcel)
        }

        override fun newArray(size: Int): Array<Beer?> {
            return arrayOfNulls(size)
        }
    }
}