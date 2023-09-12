package com.e.cocktil.presentation.screen.cocktailDetail

import android.annotation.SuppressLint
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.e.cocktil.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailDetailScreen(navController: NavHostController, viewModel: CocktailDetailViewModel = hiltViewModel()) {

   // val systemUiController = rememberSystemUiController()

    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(R.string.app_name))
            },
            //  backgroundColor = topAppbarBackgroundColor
        )
    }) {

    }
}

