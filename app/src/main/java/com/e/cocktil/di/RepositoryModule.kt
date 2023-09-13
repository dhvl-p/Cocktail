package com.e.cocktil.di

import com.e.data.database.CocktailDao
import com.e.domain.repository.CocktailDetailRepository
import com.e.domain.repository.CocktailRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.e.data.repository.CocktailDetailRepositoryImpl
import com.e.data.repository.CocktailRepositoryImpl
import com.e.data.repository.dataSource.CocktailDetailRemoteDataSource
import com.e.data.repository.dataSource.CocktailRemoteDataSource


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCocktailsRepository(cocktailRemoteDataSource: CocktailRemoteDataSource, cocktailDao: CocktailDao) : CocktailRepository=
        CocktailRepositoryImpl(cocktailRemoteDataSource,cocktailDao)

    @Provides
    fun provideCocktailDetailRepository(cocktailDetailRemoteDataSource: CocktailDetailRemoteDataSource) : CocktailDetailRepository =
        CocktailDetailRepositoryImpl(cocktailDetailRemoteDataSource)
}