package com.example.beerhive.beerlist

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.beerhive.database.BeerDatabase

class BeerViewModelFactory(private val beerDatabaseDao: BeerDatabase, private val application: Application) :  ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeerViewModel::class.java)) {
            return BeerViewModel(beerDatabase = beerDatabaseDao,application = application ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}