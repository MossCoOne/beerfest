package com.example.beerhive.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DataBaseBeer::class], version = 1, exportSchema = false)
abstract class BeerDatabase : RoomDatabase() {

    abstract val beerDatabaseDao: BeerDatabaseDao

    companion object {
        @Volatile//Make sure that changes made to the database are immediately visible on other threads
        private var INSTANCE: BeerDatabase? = null

        fun getInstance(context: Context): BeerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            BeerDatabase::class.java,
                            "beer_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}