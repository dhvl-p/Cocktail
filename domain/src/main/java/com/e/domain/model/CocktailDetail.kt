package com.e.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CocktailDetail(
    @SerializedName("idDrink")
    val idDrink: String,
    @SerializedName("strDrink")
    val strDrink: String,
    @SerializedName("strDrinkAlternate")
    val strDrinkAlternate: String,
    @SerializedName("strTags")
    val strTags: String,
    @SerializedName("strVideo")
    val strVideo: String,
    @SerializedName("strCategory")
    val strCategory: String,
    @SerializedName("strIBA")
    val strIBA: String,
    @SerializedName("strAlcoholic")
    val strAlcoholic: String,
    @SerializedName("strGlass")
    val strGlass: String,
    @SerializedName("strInstructions")
    val strInstructions: String?,
    @SerializedName("strInstructionsDE")
    val strInstructionsDE: String?,
    @SerializedName("strInstructionsIT")
    val strInstructionsIT: String?,
    @SerializedName("strDrinkThumb")
    val strDrinkThumb: String,
) : Serializable