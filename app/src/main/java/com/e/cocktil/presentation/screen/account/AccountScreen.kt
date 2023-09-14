package com.e.cocktil.presentation.screen.account

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.e.cocktil.Common
import com.e.cocktil.R
import com.e.cocktil.presentation.components.CocktailListItem
import com.e.cocktil.presentation.components.ProgressBar
import com.e.domain.model.CocktailList

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AccountScreen(viewModel: AccountViewModel = hiltViewModel()) {

    Scaffold() {
        if (Common.isInternetAvailable(LocalContext.current)) {
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
        } else {
            Column {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colorResource(id = R.color.purple_200))
                ) {
                    Text("", Modifier.weight(1f))
                    Image(
                        painterResource(R.drawable.wifi_128),
                        contentDescription = "",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.size(48.dp)
                    )
                    Text(
                        text = "Internet connection not available",
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text("", Modifier.weight(1f))
                }
            }
        }
    }
}


@Composable
fun setupHomeScreen(cocktailResponse: CocktailList) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {
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
}
