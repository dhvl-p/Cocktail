package com.e.data.repository.dataSource

import com.e.domain.model.CocktailList
import retrofit2.Response

interface CocktailRemoteDataSource {
    suspend fun getCocktails(): Response<CocktailList>
}