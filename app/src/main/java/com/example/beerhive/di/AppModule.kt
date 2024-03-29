package com.example.beerhive.di

import android.app.Application
import com.example.beerhive.beerlist.BeerRepository
import com.example.beerhive.beerlist.IBeerRepository
import com.example.beerhive.database.BeerDatabase
import com.example.beerhive.database.BeerDatabaseDao
import com.example.beerhive.network.BeerServiceApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideBeerDatabase(application: Application): BeerDatabase {
        return BeerDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun providesBeerDatabaseDao(application: Application): BeerDatabaseDao {
        return BeerDatabase.getInstance(application).beerDatabaseDao
    }

    @Provides
    @Singleton
    fun providesIBeerRepository(
        beerDatabaseDao: BeerDatabaseDao,
        beerServiceApi: BeerServiceApi,
        dispatcherProvider: DispatcherProvider
    ): IBeerRepository {
        return BeerRepository(beerDatabaseDao, beerServiceApi, dispatcherProvider.io)
    }

    @Singleton
    @Provides
    fun provideBeerServiceApi(): BeerServiceApi {
        return BeerServiceApi.create()
    }
}