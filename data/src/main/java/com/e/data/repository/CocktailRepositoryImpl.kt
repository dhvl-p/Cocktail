package com.e.data.repository

import com.e.data.database.CocktailDao
import com.e.domain.model.CocktailList
import com.e.domain.repository.CocktailRepository
import com.e.domain.util.Result
import com.e.data.repository.dataSource.CocktailRemoteDataSource
import com.e.domain.model.Cocktail
import io.reactivex.Maybe
import retrofit2.Response

class CocktailRepositoryImpl(private val cocktailRemoteDataSource: CocktailRemoteDataSource,private val cocktailDao: CocktailDao) :
    CocktailRepository {
    override suspend fun getCocktails() = responseToRequest(cocktailRemoteDataSource.getCocktails())

    override fun insertCocktail(cocktail: Cocktail): Maybe<Long> {
        return cocktailDao.insertAll(cocktail)
    }

    override fun getCocktailFromDatabase(): Maybe<List<Cocktail>> {
       return cocktailDao.getCocktailList()
    }


    private fun responseToRequest(response: Response<CocktailList>):Result<CocktailList>{
        if(response.isSuccessful){
            response.body()?.let {result->
                return Result.Success(result)
            }
        }
        return Result.Error(response.message())
    }
}