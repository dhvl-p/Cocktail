package com.e.cocktil.presentation.screen.home

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.cocktil.Common
import com.e.domain.model.Cocktail
import com.e.domain.model.CocktailList
import com.e.domain.useCase.GetCocktailsUseCase
import com.e.domain.util.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.disposables.CompositeDisposable
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

    private fun getCocktaildata() {
        viewModelScope.launch(Dispatchers.IO) {
            getCocktailUseCase.getCocktailFromDatabase()
        }
    }
}