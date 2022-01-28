package com.example.beerhive.beerlist

import androidx.lifecycle.LiveData
import com.example.beerhive.domain.Beer

interface IBeerRepository {
    fun getBeerList(): LiveData<List<Beer>>
    suspend fun refreshBeerList()
}