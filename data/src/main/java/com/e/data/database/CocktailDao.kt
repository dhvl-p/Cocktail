package com.e.data.database

import android.database.Observable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.e.domain.model.Cocktail
import io.reactivex.Maybe

@Dao
interface CocktailDao {

    @Query("SELECT * from Cocktail")
    fun getCocktailList(): Maybe<List<Cocktail>>

    @Insert
    fun insertAll(user: Cocktail): Maybe<Long>
}