package com.e.data.repository

import com.e.domain.model.CocktailDetailList
import com.e.domain.repository.CocktailDetailRepository
import com.e.domain.util.Result
import com.e.data.repository.dataSource.CocktailDetailRemoteDataSource
import retrofit2.Response

class CocktailDetailRepositoryImpl(private val cocktailDetailRemoteDataSource: CocktailDetailRemoteDataSource) :
    CocktailDetailRepository {
    override suspend fun getCocktailDetail() = responseToRequest(cocktailDetailRemoteDataSource.getCocktailDetail())

    private fun responseToRequest(response: Response<CocktailDetailList>):Result<CocktailDetailList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}