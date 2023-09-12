package com.e.cocktil.presentation.screen.home


import android.annotation.SuppressLint
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.e.cocktil.Common
import com.e.cocktil.R
import com.e.cocktil.presentation.components.CocktailListItem
import com.e.cocktil.presentation.components.ProgressBar
import com.e.cocktil.presentation.components.RatingBarComponent
import com.e.cocktil.presentation.screen.cocktailDetail.CocktailDetailActivity
import com.e.domain.model.CocktailList
import com.e.domain.model.HorizontalPagerContent


@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel()) {
    val items = createItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.purple_200))
            .wrapContentSize(Alignment.TopStart)
    ) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            pageCount = items.size,
            state = pagerState,
            verticalAlignment = Alignment.Top, //add this
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .background(Color.LightGray)
                    .fillMaxWidth()
            ) {
                Image(
                    painterResource(items[it].image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                Column {
                    Text(
                        text = "Featured Recipes",
                        color = colorResource(id = R.color.white),
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp, start = 16.dp)
                    )
                    Text(
                        text = items[it].title,
                        color = colorResource(id = R.color.white),
                        fontSize = 32.sp,
                        modifier = Modifier.padding(top = 8.dp, start = 16.dp)
                    )

                    Row {
                        RatingBarComponent(
                            rating = items[it].rating,
                            modifier = Modifier.padding(top = 12.dp, start = 16.dp)
                        )
                        Text(
                            text = getText(rating = items[it].rating),
                            color = colorResource(id = R.color.white),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 4.dp, start = 5.dp)
                        )

                        Icon(
                            painter = painterResource(id = R.drawable.ic_likes_24),
                            contentDescription = null, tint = colorResource(id = R.color.white),
                            modifier = Modifier.padding(top = 2.dp, start = 16.dp)
                        )

                        Text(
                            text = items[it].likes.toString(),
                            color = colorResource(id = R.color.white),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier.padding(top = 4.dp, start = 5.dp)
                        )
                    }

                    Text(
                        text = "",
                        modifier = Modifier.weight(1f)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 10.dp, horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(items[it].chefImage),
                            contentDescription = "avatar",
                            contentScale = ContentScale.Crop,            // crop the image if it's not a square
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)                       // clip to the circle shape
                            // .border(2.dp, Color.Gray, CircleShape)   // add a border (optional)
                        )

                        Column(Modifier.weight(1f)) {
                            Text(
                                text = items[it].chefName,
                                color = colorResource(id = R.color.white),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(start = 5.dp)
                            )
                            Text(
                                text = "Posted " + items[it].postedTime + "h ago",
                                color = colorResource(id = R.color.white),
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier.padding(top = 2.dp, start = 5.dp)
                            )
                        }


                        DotsIndicator(
                            totalDots = 5,
                            selectedIndex = pagerState.currentPage,
                            selectedColor = colorResource(id = androidx.appcompat.R.color.material_blue_grey_800),
                            unSelectedColor = colorResource(id = R.color.white),
                        )
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(4.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Today Recipes",
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .padding(top = 2.dp, start = 5.dp)
                    .weight(1f)
            )

            Text(
                text = "View All",
                color = colorResource(id = R.color.white),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(top = 2.dp, end = 5.dp)
            )
        }
        
        when (val cocktailResponse = viewModel.cocktailState.value) {
            is com.e.domain.util.Result.Loading -> ProgressBar()
            is com.e.domain.util.Result.Success -> cocktailResponse.data?.let { it1 ->
                customListView(it1)
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
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int,
    selectedColor: Color,
    unSelectedColor: Color,
) {

    LazyRow(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()

    ) {

        items(totalDots) { index ->
            if (index == selectedIndex) {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(selectedColor)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(10.dp)
                        .clip(CircleShape)
                        .background(unSelectedColor)
                )
            }

            if (index != totalDots - 1) {
                Spacer(modifier = Modifier.padding(horizontal = 2.dp))
            }
        }
    }
}

fun createItems() = listOf(
    HorizontalPagerContent(
        image = R.drawable.image1,
        chefImage = R.drawable.image2,
        title = "Kathi Roll",
        chefName = "Sanjeev Kapoor",
        postedTime = 2,
        rating = 1.0,
        likes = 50
    ),
    HorizontalPagerContent(
        image = R.drawable.image2,
        chefImage = R.drawable.image2,
        title = "Manchurian",
        chefName = "Rajat Mehta",
        postedTime = 3,
        rating = 3.0,
        likes = 100
    ),
    HorizontalPagerContent(
        image = R.drawable.image3,
        chefImage = R.drawable.image2,
        title = "Khandvi",
        chefName = "Anil Kapoor",
        postedTime = 5,
        rating = 4.0,
        likes = 200
    ),
    HorizontalPagerContent(
        image = R.drawable.image4,
        chefImage = R.drawable.image2,
        title = "Cocktail",
        chefName = "Dhaval Parekh",
        postedTime = 8,
        rating = 5.0,
        likes = 2500
    ),
    HorizontalPagerContent(
        image = R.drawable.image5,
        chefImage = R.drawable.image2,
        title = "Cheese Ball",
        chefName = "Sachin Shah",
        postedTime = 12,
        rating = 2.0,
        likes = 340
    ),
)

@Composable
fun getText(rating: Double): String {
    if (rating == 1.0) return "Poor"
    else if (rating == 2.0) return "Average"
    else if (rating == 3.0) return "Good"
    else if (rating == 4.0) return "Strong"
    else return "Super"

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun customListView(list: CocktailList) {
    val context = LocalContext.current
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)
    ) {
        items(
            items = list.drinks,
            itemContent = {
                Card(
                    // inside our grid view on below line
                    // we are adding on click for each item of our grid view.
                    // in the below line, we are adding
                    // padding from our all sides.
                    modifier = Modifier
                        .width(120.dp)
                        .padding(4.dp)
                        .height(120.dp),
                    onClick = {
                        val intent = Intent(context, CocktailDetailActivity::class.java)
                        intent.putExtra("Name", it.strDrink)
                        context.startActivity(intent)
                    }

                    // in the below line, we are adding
                    // elevation for the card.

                )
                {
                    // in the below line, we are creating
                    // a row for our list view item.
                    Column(
                        // for our row we are adding modifier
                        // to set padding from all sides.
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // in the below line, inside row we are adding spacer
                        // Spacer(modifier = Modifier.height(5.dp))

                        // in the below line, we are adding Image to display the image.
                        val painter =
                            rememberAsyncImagePainter(model = it.strDrinkThumb)
                        Image(
                            painter = painter,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        )
    }
}

