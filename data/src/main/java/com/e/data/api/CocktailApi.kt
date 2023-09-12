package com.e.data.api

import com.e.domain.model.CocktailDetailList
import com.e.domain.model.CocktailList
import retrofit2.Response
import retrofit2.http.GET

interface CocktailApi {
    @GET("filter.php?c=Cocktail")
    suspend fun getCocktailList(): Response<CocktailList>

    @GET("lookup.php?i=15346")
    suspend fun getCocktailDetail(): Response<CocktailDetailList>
}