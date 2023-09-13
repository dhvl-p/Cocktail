package com.e.cocktil.di

import android.content.Context
import androidx.room.Room
import com.e.data.database.CocktailDao
import com.e.data.database.MyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    fun provideMyDatabase(@ApplicationContext context: Context): MyDatabase {
        return Room.databaseBuilder(context,MyDatabase::class.java,"cocktaildb")
            .build()
    }

    @Provides
    fun provideCocktailDao(myDatabase: MyDatabase): CocktailDao {
        return myDatabase.getCocktailDao()
    }
}