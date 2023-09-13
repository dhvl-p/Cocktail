package com.e.cocktil.presentation.screen.search

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cocktil.Common
import com.e.domain.model.CocktailList
import com.e.domain.useCase.GetCocktailsUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailsUseCase, @ApplicationContext context: Context
) : ViewModel() {
    private val _cocktailState = mutableStateOf<Result<CocktailList>>(Result.Loading())
    val cocktailState: State<Result<CocktailList>> = _cocktailState

    init {
        if (Common.isInternetAvailable(context))
            getCocktailList()
    }

    private fun getCocktailList() {
        viewModelScope.launch {
            _cocktailState.value = getCocktailUseCase()
        }
    }
}