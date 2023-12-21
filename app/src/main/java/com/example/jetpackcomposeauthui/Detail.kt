package com.example.jetpackcomposeauthui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackcomposeauthui.components.CButton
import com.example.jetpackcomposeauthui.ui.theme.AlegreyaFontFamily
import com.example.travelapp.ui.features.HomeHeader
import com.example.travelapp.ui.features.HomeTripItem
import com.example.travelapp.ui.features.tripListing

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun Detail(navController: NavController, index: Int) {

    val homeTripModel: HomeTripModel = tripListing[index]


        Column(modifier = Modifier.fillMaxSize().background(
                Brush.verticalGradient(
                    listOf(Color.Cyan, Color.White),
                    startY = 90.0f,
                    endY = 100.0f
                )
        )) {

            GlideImage(
                model = homeTripModel.image,
                contentDescription = "Banner",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 10))
                    .height(250.dp)
//                    .align(alignment = Alignment)
            )
//            Spacer(modifier = Modifier.weight(1f))

            Text(
                text = homeTripModel.title,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 30.sp,
            )

            Text(
                text = "\n"+homeTripModel.location+"\n",
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            )

            Text(
                text = homeTripModel.jenisWisata+"\n",
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            )

            Text(
                text = "About",
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 22.sp,
            )

            Text(
                text = homeTripModel.description,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
            )

            CButton(text = "Ulasan Pengunjung",
                onClick = {
                    navController.navigate("login")
                }
            )

            Spacer(modifier = Modifier.weight(1f))


            Row (
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ){

                Text(text = "20k/people \n +Parking ",fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,)

                Spacer(modifier = Modifier.weight(1f))

                CButton(text = "Booking Sekarang",
                    onClick = {
                        navController.navigate("login")
                    }
                )
            }
        }

}