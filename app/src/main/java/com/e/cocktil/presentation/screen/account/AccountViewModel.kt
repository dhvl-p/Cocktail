package com.e.cocktil.presentation.screen.account

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.domain.model.CocktailList
import com.e.domain.useCase.GetCocktailsUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val getCocktailUseCase: GetCocktailsUseCase
) : ViewModel() {
    private val _cocktailState = mutableStateOf<Result<CocktailList>>(Result.Loading())
    val cocktailState: State<Result<CocktailList>> = _cocktailState
    init {
        getCocktailList()
    }

    private fun getCocktailList() {
        viewModelScope.launch {
            _cocktailState.value =  getCocktailUseCase()
        }
    }
}