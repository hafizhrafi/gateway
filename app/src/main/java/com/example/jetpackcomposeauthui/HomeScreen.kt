package com.example.travelapp.ui.features

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeauthui.R
import com.example.jetpackcomposeauthui.ui.theme.AlegreyaFontFamily

@Composable
fun HomeScreen(navController: NavController) {


    LazyColumn(modifier = Modifier.fillMaxWidth()) {


        item {
            HomeHeader()
        }

        item {
            Text(
                text = "Liburan Terbaru",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 16.dp,
                    bottom = 16.dp
                ),
            )
        }

        itemsIndexed(tripListing) { position, data ->
            HomeTripItem(homeTripModel = data,navController = navController)
        }

        item {
            Spacer(modifier = Modifier.navigationBarsPadding())
        }

    }

}


@Composable
@Preview
fun HomeHeader() {

    val homeHeaderBg = "https://www.4freephotos.com/medium/2015/Blue-blurry-background-5731.jpg"

    Box {

        Image(
            painter = painterResource(id = R.drawable.bg),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(300.dp)
                .alpha(0.2f)
                .fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .statusBarsPadding()
                .padding(16.dp)
        ) {

            Text(
                text = "Haiii sayangku",
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 38.sp,
                letterSpacing = (-1).sp
            )

            Text(
                text = "Mau Kemana Hari Ini??",
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 18.sp,
                lineHeight = 24.sp,
                letterSpacing = ((-0.2).sp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {


//                VerticalButton(vector = Icons.Filled.AirplanemodeActive, text = "Flights")
//                VerticalButton(vector = Icons.Filled.DirectionsCar, text = "Cars")
//                VerticalButton(vector = Icons.Filled.Business, text = "Hotel")
//                VerticalButton(vector = Icons.Filled.LocalShipping, text = "Cruise")

            }


        }


    }

}



data class HomeTripModel(
    val image: String,
    val title: String,
    val location: String,
    val rating: Float
)

@Composable
fun HomeTripItem(homeTripModel: HomeTripModel,navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {

        Image(
            painter =painterResource(id = R.drawable.candi),
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .clickable {
                    navController.navigate("detail")
                }
                .height(200.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row() {

            Text(
                text = homeTripModel.location,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = homeTripModel.rating.toString(),
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp
            )
        }

        Text(
            text = homeTripModel.title,
            fontFamily = AlegreyaFontFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp,
            lineHeight = 24.sp
        )

    }


}

val tripListing = listOf<HomeTripModel>(
    HomeTripModel(
        "R.drawable.candi.png",
        "Candi terbaru hanya 45k",
        "Candi terbaru hanya 45k",
        4.8f
    ),

    HomeTripModel(
        "https://d3hne3c382ip58.cloudfront.net/files/uploads/bookmundi/resized/cmsfeatured/south-india-tour-package-1555403191-785X440.jpg",
        "2 hari / 2 orang",
        "Sungai terbaru hanya 50k ",
        5.9f
    ),

    HomeTripModel(
        "https://imgcld.yatra.com/ytimages/image/upload/t_holidays_srplist_tablet_hc/v1501843603/DO_NOT_USE_Editorial_Images/Grand_palace_and_Wat_phra_keaw.jpg",
        "1 hari / 2 orang",
        "Pasar Induk Among Tani",
        4.8f
    ),

    )