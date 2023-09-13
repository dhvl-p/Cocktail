package com.e.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.e.domain.model.Cocktail

const val MY_DATA_BASE = "cocktaildb"

@Database(entities = [Cocktail::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun getCocktailDao(): CocktailDao
}