package com.e.cocktil.presentation.screen.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cocktil.Common
import com.e.domain.model.Cocktail
import com.e.domain.model.CocktailList
import com.e.domain.useCase.GetCocktailsUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailsUseCase, @ApplicationContext context: Context
) : ViewModel() {


    private val _cocktailState = mutableStateOf<Result<CocktailList>>(Result.Loading())
    val cocktailState: State<Result<CocktailList>> = _cocktailState

    init {
        if(Common.isInternetAvailable(context))
        getCocktailList()
    }

    private fun getCocktailList() {
        viewModelScope.launch {
            _cocktailState.value = getCocktailUseCase()
        }
    }

    fun insertCocktailData(cocktail: Cocktail) {
        viewModelScope.launch(Dispatchers.IO) {
            getCocktailUseCase.insertCocktail(cocktail)
        }
    }

     fun getCocktaildataFromDatbase(): List<Cocktail> {
         var cocktailList : List<Cocktail> = listOf()
        viewModelScope.launch(Dispatchers.IO) {
           cocktailList =  getCocktailUseCase.getCocktailFromDatabase()
        }
         return cocktailList
    }
}