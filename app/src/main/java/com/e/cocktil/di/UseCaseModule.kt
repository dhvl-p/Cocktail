package com.e.cocktil.di

import com.e.domain.repository.CocktailDetailRepository
import com.e.domain.repository.CocktailRepository
import com.e.domain.useCase.GetCocktailDetailUseCase
import com.e.domain.useCase.GetCocktailsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetCocktailsUseCase(cocktailRepository: CocktailRepository) =
       GetCocktailsUseCase(cocktailRepository)

    @Provides
    fun provideGetCocktailDetailUseCase(cocktailDetailRepository: CocktailDetailRepository) =
        GetCocktailDetailUseCase(cocktailDetailRepository)
}