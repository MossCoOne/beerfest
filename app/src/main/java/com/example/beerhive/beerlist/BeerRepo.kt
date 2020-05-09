package com.example.beerhive.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.beerhive.database.BeerDatabase
import com.example.beerhive.database.asDomainModel
import com.example.beerhive.domain.Beer
import com.example.beerhive.network.BeerServiceApiClient
import com.example.beerhive.network.model.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

    class BeerRepo(private val database: BeerDatabase) {


    val beerList: LiveData<List<Beer>> =
            Transformations.map(database.beerDatabaseDao.getAllBeers()) {
                it.asDomainModel()
            }

    suspend fun refreshBeerList() {
        withContext(Dispatchers.IO) {
            val beerList = BeerServiceApiClient.beerServiceApi.getListOfBeersAsync().await()
            Log.d("XXXXX",beerList.toString())
            database.beerDatabaseDao.insertAll(*beerList.asDataBaseModel())
        }
    }


}