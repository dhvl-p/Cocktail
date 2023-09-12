package com.e.composedemo

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.e.cocktil.R
import com.e.cocktil.presentation.components.ProgressBar
import com.e.cocktil.presentation.screen.cocktailDetail.CocktailDetailActivity
import com.e.cocktil.presentation.screen.home.HomeViewModel
import com.e.cocktil.presentation.screen.home.customListView
import com.e.cocktil.presentation.screen.search.SearchViewModel
import com.e.domain.model.CocktailList

@Composable
fun SearchScreen(viewModel: SearchViewModel = hiltViewModel()) {
    when (val cocktailResponse = viewModel.cocktailState.value) {
        is com.e.domain.util.Result.Loading -> ProgressBar()
        is com.e.domain.util.Result.Success -> cocktailResponse.data?.let { it1 ->
            customGridView(it1)
        }

        is com.e.domain.util.Result.Error -> Toast.makeText(
            LocalContext.current,
            stringResource(R.string.toast_error),
            Toast.LENGTH_SHORT
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customGridView(cocktailList: CocktailList) {
    val context = LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }
    Column {
        SearchBar()

        LazyVerticalGrid(columns = GridCells.Adaptive(minSize = 128.dp),
            contentPadding = PaddingValues(
                start = 12.dp,
                top = 16.dp,
                end = 12.dp,
                bottom = 16.dp
            ),
            content = {
                items(
                    items = cocktailList.drinks,
                    itemContent = {
                        Card(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(4.dp)
                                .height(300.dp),
                            onClick = {
                                val intent = Intent(context, CocktailDetailActivity::class.java)
                                intent.putExtra("Name", it.strDrink)
                                context.startActivity(intent)
                            }

                        )
                        {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                            ) {
                                // Spacer(modifier = Modifier.height(5.dp))
                                val painter =
                                    rememberAsyncImagePainter(model = it.strDrinkThumb)
                                Image(
                                    painter = painter,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .weight(1f),
                                    contentScale = ContentScale.Crop
                                )

                                Text(
                                    maxLines = 1,
                                    text = it.strDrink,
                                    color = colorResource(id = R.color.white),
                                    fontSize = 16.sp,
                                    modifier = Modifier.padding(top = 6.dp, bottom = 6.dp),
                                    textAlign = TextAlign.Center,

                                    )
                            }
                        }
                    }
                )
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier
) {
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        colors = TextFieldDefaults.textFieldColors(

        ),
        placeholder = {
            Text(stringResource(R.string.search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
    )
}