package com.e.cocktil.di

import com.e.data.api.CocktailApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.e.data.repository.dataSource.CocktailDetailRemoteDataSource
import com.e.data.repository.dataSource.CocktailRemoteDataSource
import com.e.data.repository.dataSourceImpl.CocktailDetailRemoteDataSourceImpl
import com.e.data.repository.dataSourceImpl.CocktailRemoteDataSourceImpl


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideCocktailsRemoteDataSource(cocktailApi: CocktailApi) : CocktailRemoteDataSource =
        CocktailRemoteDataSourceImpl(cocktailApi)

    @Provides
    fun provideCocktailDetailRemoteDataSource(cocktailApi: CocktailApi) : CocktailDetailRemoteDataSource =
        CocktailDetailRemoteDataSourceImpl(cocktailApi)
}