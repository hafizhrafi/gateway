package com.example.jetpackcomposeauthui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackcomposeauthui.ui.theme.AlegreyaFontFamily
import com.example.travelapp.ui.features.HomeTripItem
import com.example.travelapp.ui.features.tripListing
import java.util.Locale


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Ulasan(index: Int, navController: NavController) {
    val homeTripModel: HomeTripModel = tripListing[index]

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF0FFF9), Color(0xFF94D4CB)),
                )
            )
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        item { GlideImage(
            model = homeTripModel.image,
            contentDescription = "Banner",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .clip(RoundedCornerShape(percent = 10))
                .height(250.dp)
//                    .align(alignment = Alignment)
        ) }
        item {
            Text(
                text = homeTripModel.title,
                style = TextStyle(
                    fontSize = 33.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF042F23),
                )
            )
        }

        item { Spacer(modifier = Modifier.height(6.dp)) }

        item {
            Text(
                text = homeTripModel.location,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 30.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )
        }
        
        item { Spacer(modifier = Modifier.height(6.dp)) }

        item { Text(
            text = "Ulasan Pengunjung",
            style = TextStyle(
                fontSize = 30.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFF042D21),
            )
        ) }

        item { Text(
            text = homeTripModel.reviewList.size.toString() +" ulasan ",
            style = TextStyle(
                fontSize = 12.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        ) }

        itemsIndexed(homeTripModel.reviewList) { position, data ->
            reviewItem(reviewModel = data, navController = navController)
        }
    }
}

@Composable
fun reviewItem(reviewModel: DestinationReviewModel, navController: NavController) {
    val context = LocalContext.current

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6DAAA3)
        ),
        modifier = Modifier
            .padding(vertical = 6.dp)


    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF196A61), Color(0xBF196A61)),
                    )
                )
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            Row() {

                Text(
                    text = reviewModel.user,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    style = TextStyle(
                        fontWeight = FontWeight(700),
                        color = Color(0xFFF0FFF9),
                    )
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Rate: " + reviewModel.rate,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                    style = TextStyle(
                        fontWeight = FontWeight(700),
                        color = Color(0xFFF0FFF9),
                    )
                )
            }

            Text(
                text = reviewModel.review,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.SemiBold,
                style = TextStyle(
                    fontSize = 14.5.sp,
                    lineHeight = 23.sp,
                    fontWeight = FontWeight(500),
                    color = Color(0xFFF0FFF9),
                )
            )
        }
    }


}