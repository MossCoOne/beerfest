package com.example.beerhive.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.beerhive.database.BeerDatabaseDao
import com.example.beerhive.database.asDomainModel
import com.example.beerhive.domain.Beer
import com.example.beerhive.network.BeerServiceApi
import com.example.beerhive.network.model.asDataBaseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val beerDatabaseDao: BeerDatabaseDao,
    private val beerServiceApi: BeerServiceApi,
    private val ioDispatcher: CoroutineDispatcher
) : IBeerRepository {

    override fun getBeerList(): LiveData<List<Beer>> {
        return beerDatabaseDao.getAllBeers().map { it.asDomainModel() }
    }

    override suspend fun refreshBeerList() {
        withContext(ioDispatcher) {
            val results = beerServiceApi.getListOfBeers()
            if (results.isSuccessful) {
                val beerList = results.body()
                beerList?.let {
                    beerDatabaseDao.insertAll(*it.asDataBaseModel())
                }
                Log.d("XXXXX", results.body().toString())
            } else {
                Log.d("Error", results.body().toString())
            }
        }
    }


}