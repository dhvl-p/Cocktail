package com.e.data.repository

import com.e.domain.model.CocktailList
import com.e.domain.repository.CocktailRepository
import com.e.domain.util.Result
import com.e.data.repository.dataSource.CocktailRemoteDataSource
import retrofit2.Response

class CocktailRepositoryImpl(private val cocktailRemoteDataSource: CocktailRemoteDataSource) :
    CocktailRepository {
    override suspend fun getCocktails() = responseToRequest(cocktailRemoteDataSource.getCocktails())

    private fun responseToRequest(response: Response<CocktailList>):Result<CocktailList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}