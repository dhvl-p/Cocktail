package com.e.cocktil.presentation.screen.cocktailDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.domain.model.CocktailDetailList
import com.e.domain.model.CocktailList
import com.e.domain.useCase.GetCocktailDetailUseCase
import com.e.domain.useCase.GetCocktailsUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailDetailUseCase: GetCocktailDetailUseCase
) : ViewModel() {
    private val _coccktailState = mutableStateOf<Result<CocktailDetailList>>(Result.Loading())
    val cocktailState: State<Result<CocktailDetailList>> = _coccktailState
    init {
        getCocktailDetail()
    }

    private fun getCocktailDetail() {
        viewModelScope.launch {
            _coccktailState.value =  getCocktailDetailUseCase()
        }
    }
}