package com.e.domain.repository
import com.e.domain.model.Cocktail
import com.e.domain.model.CocktailList
import com.e.domain.util.Result
import io.reactivex.Maybe

interface CocktailRepository {
     suspend fun getCocktails(): Result<CocktailList>
     fun insertCocktail(cocktail: Cocktail)

     fun getCocktailFromDatabase() : List<Cocktail>
}