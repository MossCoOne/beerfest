package com.example.beerhive.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.beerhive.database.BeerDatabaseDao
import com.example.beerhive.database.asDomainModel
import com.example.beerhive.domain.Beer
import com.example.beerhive.network.BeerServiceApiClient
import com.example.beerhive.network.model.asDataBaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerRepository @Inject constructor(private val beerDatabaseDao: BeerDatabaseDao) :
    IBeerRepository {

    override fun getBeerList(): LiveData<List<Beer>> {
        return Transformations.map(beerDatabaseDao.getAllBeers()) {
            it.asDomainModel()
        }
    }

    override suspend fun refreshBeerList() {
        withContext(Dispatchers.IO) {
            val beerList = BeerServiceApiClient.beerServiceApi.getListOfBeersAsync().await()
            Log.d("XXXXX", beerList.toString())
            beerDatabaseDao.insertAll(*beerList.asDataBaseModel())
        }
    }


}