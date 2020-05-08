package com.example.beerhive.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Beer(
        @PrimaryKey val uid: Int,
        var beerName: String?,
        var beerDescription: String?,
        var beerBrewerTips: String?,
        var beerImageUrl: String?,
        var beerBrewingTipsTitle: String?)