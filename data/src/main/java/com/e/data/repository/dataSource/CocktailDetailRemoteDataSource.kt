package com.e.data.repository.dataSource

import com.e.domain.model.CocktailDetailList
import retrofit2.Response

interface CocktailDetailRemoteDataSource {
    suspend fun getCocktailDetail(): Response<CocktailDetailList>
}