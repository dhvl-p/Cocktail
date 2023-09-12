package com.e.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Cocktail(
    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
    @SerializedName("idDrink")
    val idDrink: String,
) : Serializable