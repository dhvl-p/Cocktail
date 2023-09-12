package com.e.domain.useCase

import com.e.domain.repository.CocktailDetailRepository


class GetCocktailDetailUseCase(private val cocktailDetailRepository: CocktailDetailRepository) {
     suspend operator fun invoke()=cocktailDetailRepository.getCocktailDetail()
}