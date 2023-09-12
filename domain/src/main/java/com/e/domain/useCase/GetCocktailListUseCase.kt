package com.e.domain.useCase

import com.e.domain.repository.CocktailRepository


class GetCocktailsUseCase(private val cocktailRepository: CocktailRepository) {
     suspend operator fun invoke()=cocktailRepository.getCocktails()
}