package com.e.cocktil.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.e.cocktil.R
import com.e.domain.model.Cocktail

@Composable
fun CocktailListItem(cocktail: Cocktail) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .fillMaxWidth()
            .height(100.dp),
    ) {
        Box(
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxWidth()
                .height(100.dp)
        ) {
            val painter =
                rememberAsyncImagePainter(model = cocktail.strDrinkThumb)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Text(
                text = cocktail.strDrink,
                color = colorResource(id = R.color.white),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 4.dp, start = 5.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))

        }
    }
}