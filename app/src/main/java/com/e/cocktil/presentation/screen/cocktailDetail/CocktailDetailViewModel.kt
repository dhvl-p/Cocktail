package com.e.cocktil.presentation.screen.cocktailDetail

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cocktil.Common
import com.e.domain.model.CocktailDetailList
import com.e.domain.useCase.GetCocktailDetailUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CocktailDetailViewModel @Inject constructor(
    private val getCocktailDetailUseCase: GetCocktailDetailUseCase,@ApplicationContext context: Context
) : ViewModel() {
    private val _coccktailState = mutableStateOf<Result<CocktailDetailList>>(Result.Loading())
    val cocktailState: State<Result<CocktailDetailList>> = _coccktailState
    init {
        if(Common.isInternetAvailable(context))
        getCocktailDetail()
    }

    private fun getCocktailDetail() {
        viewModelScope.launch {
            _coccktailState.value =  getCocktailDetailUseCase()
        }
    }
}