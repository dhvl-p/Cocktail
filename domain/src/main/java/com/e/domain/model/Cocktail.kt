package com.e.domain.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Cocktail")
data class Cocktail(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    var id: Int,

    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
    @SerializedName("idDrink")
    val idDrink: String,
) : Serializable