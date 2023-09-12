package com.e.data.repository.dataSourceImpl

import com.e.data.api.CocktailApi
import com.e.data.repository.dataSource.CocktailRemoteDataSource

class CocktailRemoteDataSourceImpl(private val cocktailApi: CocktailApi): CocktailRemoteDataSource {
    override suspend fun getCocktails()= cocktailApi.getCocktailList()
}