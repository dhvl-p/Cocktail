package com.e.data.database

import android.database.Observable
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.e.domain.model.Cocktail
import io.reactivex.Maybe
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Query("SELECT * from Cocktail")
    fun getCocktailList(): List<Cocktail>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(user: Cocktail)
}