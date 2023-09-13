package com.e.data.api

import com.e.domain.model.CocktailDetailList
import com.e.domain.model.CocktailList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CocktailApi {
    @GET("filter.php?c=Cocktail")
    suspend fun getCocktailList(): Response<CocktailList>

    //@GET("lookup.php?i=15346")
    @GET("lookup.php")
    suspend fun getCocktailDetail(@Query("i") id:String): Response<CocktailDetailList>
}