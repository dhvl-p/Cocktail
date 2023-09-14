package com.e.domain.useCase

import com.e.domain.model.Cocktail
import com.e.domain.repository.CocktailRepository
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class GetCocktailsUseCase(private val cocktailRepository: CocktailRepository) {
    suspend operator fun invoke() = cocktailRepository.getCocktails()

    fun insertCocktail(cocktail: Cocktail) =
        cocktailRepository.insertCocktail(cocktail)
       /* cocktailRepository.isDataExist(cocktail.idDrink)
            .flatMap {
                if (it == 0) {
                    return@flatMap cocktailRepository.insertUser(cocktail)
                } else {
                    return@flatMap null
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())*/



    fun getCocktailFromDatabase(): List<Cocktail> {
        return cocktailRepository.getCocktailFromDatabase()
    }
    /* fun getCocktail(cocktail: List<Cocktail>): Maybe<List<Cocktail>> = cocktailRepository.isDataExist(cocktail.idDrink)
         .flatMap{
             return@flatMap cocktailRepository.getCocktailFromDatabase(cocktail)
         }
         .subscribeOn(Schedulers.io())
         .observeOn(AndroidSchedulers.mainThread())*/
}