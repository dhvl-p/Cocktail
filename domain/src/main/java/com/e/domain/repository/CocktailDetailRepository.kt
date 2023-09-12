package com.e.domain.repository
import com.e.domain.model.CocktailDetailList
import com.e.domain.model.CocktailList
import com.e.domain.util.Result

interface CocktailDetailRepository {
     suspend fun getCocktailDetail(): Result<CocktailDetailList>
}