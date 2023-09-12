package com.e.cocktil.presentation.screen.account

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.e.cocktil.R
import com.e.cocktil.presentation.components.CocktailListItem
import com.e.cocktil.presentation.components.ProgressBar
import com.e.domain.model.CocktailList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(viewModel: AccountViewModel = hiltViewModel()) {

    // val systemUiController = rememberSystemUiController()
    SideEffect {
        /* systemUiController.setStatusBarColor(
             color = systemBarColor
         )*/
    }

    Scaffold() {
        when (val cocktailResponse = viewModel.cocktailState.value) {
            is com.e.domain.util.Result.Loading -> ProgressBar()
            is com.e.domain.util.Result.Success -> cocktailResponse.data?.let { it1 ->
                setupHomeScreen(it1)
            }

            is com.e.domain.util.Result.Error -> Toast.makeText(
                LocalContext.current,
                stringResource(R.string.toast_error),
                Toast.LENGTH_SHORT
            )
        }
    }
}

@Composable
fun setupHomeScreen(cocktailResponse: CocktailList) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(
            items = cocktailResponse.drinks,
            itemContent = {
                CocktailListItem(cocktail = it)
            }
        )
    }
}
