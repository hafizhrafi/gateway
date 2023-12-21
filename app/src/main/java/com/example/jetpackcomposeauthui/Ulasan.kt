package com.example.jetpackcomposeauthui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.jetpackcomposeauthui.ui.theme.AlegreyaFontFamily


@Composable
fun Ulasan(destinationReviewModel: DestinationReviewModel ,navController: NavController){
    Card (
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .padding(4.dp)
            .padding(6.dp)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Row {
               Text(
                       text = destinationReviewModel.user,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                )

                Text(
                    text = destinationReviewModel.user,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )

                Text(
                    text = destinationReviewModel.review,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }
        }
    }
}