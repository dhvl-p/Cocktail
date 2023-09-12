package com.e.cocktil.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import com.e.cocktil.R
import java.lang.StrictMath.ceil
import java.lang.StrictMath.floor

@Composable
fun RatingBarComponent(
    modifier: Modifier = Modifier,
    rating: Double = 0.0,
    stars: Int = 5,
) {
    val filledStars = floor(rating).toInt()
    val unfilledStars = (stars - ceil(rating)).toInt()
    Row(modifier = modifier) {
        repeat(filledStars) {
            Icon( painter = painterResource(id = R.drawable.circle_black_8), contentDescription = null, tint = colorResource(
                id = R.color.white
            ))
        }

        repeat(unfilledStars) {
            Icon(
                painter = painterResource(id = R.drawable.circle_black_8),
                contentDescription = null, tint = colorResource(id = R.color.grey)
            )
        }
    }
}