package com.e.domain.repository
import com.e.domain.model.CocktailList
import com.e.domain.util.Result

interface CocktailRepository {
     suspend fun getCocktails(): Result<CocktailList>
}