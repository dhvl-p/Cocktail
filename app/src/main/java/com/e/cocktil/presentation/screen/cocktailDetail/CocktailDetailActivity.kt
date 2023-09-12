package com.e.cocktil.presentation.screen.cocktailDetail

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.e.cocktil.R
import com.e.cocktil.presentation.components.ProgressBar
import com.e.cocktil.ui.theme.CocktilTheme
import com.e.domain.model.CocktailDetail
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CocktailDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CocktilTheme {
                CocktailDetailScreen()
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CocktailDetailScreen(viewModel: CocktailDetailViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val activity = (context as? Activity)
    val intent = (context as CocktailDetailActivity).intent
    val name = intent?.getStringExtra("Name")
    /*  navigationIcon = {
          IconButton(onClick = { activity?.finish() }) {
              Icon(
                  imageVector = Icons.Filled.ArrowBack,
                  contentDescription = "Back",
                  tint = colorResource(id = R.color.white)
              )
          }
      },*/
    Scaffold(
        // Creating a Top Bar
         topBar = {
             TopAppBar(title = { Text(name.toString(), color = Color.White) },
                 colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = colorResource(id = R.color.purple_200)),
                 navigationIcon = {
                     IconButton(onClick = { activity?.finish() }) {
                         Icon(
                             imageVector = Icons.Filled.ArrowBack,
                             contentDescription = "Back",
                             tint = colorResource(id = R.color.white)
                         )
                     }
                 }
             )
         },

        // Creating Content
        content = {
            when (val cocktailResponse = viewModel.cocktailState.value) {
                is com.e.domain.util.Result.Loading -> ProgressBar()
                is com.e.domain.util.Result.Success -> cocktailResponse.data?.let { it1 ->
                    cocktailDetail(it1.drinks.get(0))
                }

                is com.e.domain.util.Result.Error -> Toast.makeText(
                    LocalContext.current,
                    stringResource(R.string.toast_error),
                    Toast.LENGTH_SHORT
                )
            }
            // Creating a Column Layout

        }
    )

}

@Composable
fun cocktailDetail(cocktailDetail: CocktailDetail) {
    Column(
        Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

        ) {

        // Creating a Scrollable list of 100 items
        val lazyListState = rememberLazyListState()
        var scrolledY = 0f
        var previousOffset = 0
        LazyColumn(
            Modifier.fillMaxSize(),
            lazyListState,
        ) {
            item {
                Image(
                    painter = rememberAsyncImagePainter(model = cocktailDetail.strDrinkThumb),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .graphicsLayer {
                            scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                            translationY = scrolledY * 0.5f
                            previousOffset = lazyListState.firstVisibleItemScrollOffset
                        }
                        .height(400.dp)
                        .fillMaxWidth()
                )

                Text(
                    "Steps",
                    color = colorResource(id = R.color.white),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 10.dp, end = 8.dp, start = 20.dp)
                )
                Row {
                    Text(
                        "01",
                        color = colorResource(id = R.color.teal_200),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 10.dp, end = 8.dp, start = 20.dp)
                    )
                    Text(
                        cocktailDetail.strGlass,
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 10.dp, end = 4.dp)
                    )
                }


                Row {
                    Text(
                        "02",
                        color = colorResource(id = R.color.teal_200),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 4.dp, start = 20.dp)
                    )
                    Text(
                        cocktailDetail.strInstructions,
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 2.dp)
                    )
                }
                Row {
                    Text(
                        "03",
                        color = colorResource(id = R.color.teal_200),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 4.dp, start = 20.dp)
                    )
                    Text(
                        cocktailDetail.strInstructionsDE,
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 2.dp)
                    )
                }

                Row {
                    Text(
                        "04",
                        color = colorResource(id = R.color.teal_200),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 2.dp, start = 20.dp)
                    )
                    Text(
                        cocktailDetail.strInstructionsIT,
                        color = colorResource(id = R.color.white),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp, end = 2.dp)
                    )
                }

            }
        }
    }
}



