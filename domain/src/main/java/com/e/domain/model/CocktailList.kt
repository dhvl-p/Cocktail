package com.e.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CocktailList(
    @SerializedName("drinks")
    val drinks: List<Cocktail>
): Serializable