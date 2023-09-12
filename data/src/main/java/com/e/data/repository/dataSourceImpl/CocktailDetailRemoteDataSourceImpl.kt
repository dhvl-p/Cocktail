package com.e.data.repository.dataSourceImpl

import com.e.data.api.CocktailApi
import com.e.data.repository.dataSource.CocktailDetailRemoteDataSource

class CocktailDetailRemoteDataSourceImpl(private val cocktailApi: CocktailApi):
    CocktailDetailRemoteDataSource {
    override suspend fun getCocktailDetail()= cocktailApi.getCocktailDetail()
}