package com.example.beerhive.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.beerhive.domain.Beer
import com.example.beerhive.network.model.BeerResponse

@Entity(indices = [Index(value = ["beerImageUrl"], unique = true)])
data class DataBaseBeer constructor(
        val beerName: String,
        val beerDescription: String,
        val beerBrewerTips: String,
        @PrimaryKey
        val beerImageUrl: String,
        val beerBrewingTipsTitle: String,
        val volume: Int,
        val volumeUnit: String)

fun List<DataBaseBeer>.asDomainModel(): List<Beer> {
    return map {
        Beer(
                beerName = it.beerName,
                beerDescription = it.beerDescription,
                beerBrewerTips = it.beerBrewerTips,
                beerImageUrl = it.beerImageUrl,
                beerBrewingTipsTitle = it.beerBrewingTipsTitle,
                volume = it.volume,
                volumeUnit = it.volumeUnit)
    }
}