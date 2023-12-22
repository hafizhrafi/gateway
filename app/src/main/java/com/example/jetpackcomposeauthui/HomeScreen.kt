package com.example.travelapp.ui.features

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.jetpackcomposeauthui.HomeTripModel
import com.example.jetpackcomposeauthui.R
import com.example.jetpackcomposeauthui.hutanReview
import com.example.jetpackcomposeauthui.kampungReview
import com.example.jetpackcomposeauthui.museumMpuReview
import com.example.jetpackcomposeauthui.museumReview
import com.example.jetpackcomposeauthui.rainbowReview
import com.example.jetpackcomposeauthui.ui.theme.AlegreyaFontFamily
import java.util.Locale

@Composable
fun HomeScreen(navController: NavController) {


    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFFF0FFF9), Color(0xFF94D4CB)),
                )
            )
    ) {


        item {
            HomeHeader()
        }

        item {
            Text(
                text = "Rekomendasi Liburan Untukmu",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(
                    top = 20.dp,
                    start = 16.dp,
                    bottom = 16.dp
                ),
            )
        }

        itemsIndexed(tripListing) { position, data ->
            HomeTripItem(homeTripModel = data, navController = navController)
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
                .height(200.dp)
//                .alpha(0.2f)
                .fillMaxWidth()
        )


        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .statusBarsPadding()
                .padding(16.dp)
        ) {

            Text(
                text = "Haii User",
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


@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HomeTripItem(homeTripModel: HomeTripModel, navController: NavController) {
    val context = LocalContext.current

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF6DAAA3)
        ),
        modifier = Modifier
            .padding(4.dp)
            .padding(6.dp)
            .clickable {
                navController.currentBackStackEntry?.savedStateHandle?.set(
                    "index",
                    tripListing.indexOf(homeTripModel)
                )
                navController.navigate("detail")
            }

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {

            GlideImage(
                model = homeTripModel.image,
                contentDescription = "Banner",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .clip(RoundedCornerShape(percent = 10))
                    .height(250.dp)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row() {

                Text(
                    text = homeTripModel.location,
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp,
                )

                Spacer(modifier = Modifier.weight(1f))

                fun getRatingAverage(): String {
                    val rateAverage: Double = homeTripModel.reviewList.map { it.rate }.average()
                    return "%,.2f".format(Locale.ENGLISH, rateAverage)
                }

                Text(
                    text = "Rate: " + getRatingAverage(),
                    fontFamily = AlegreyaFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            }

            Text(
                text = homeTripModel.title,
                fontFamily = AlegreyaFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 18.sp,
                lineHeight = 24.sp
            )

            Spacer(modifier = Modifier.height(12.dp))
            Box( contentAlignment = Alignment.Center) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .border(
                            width = 2.dp,
                            color = Color(0xFF02231A),
                            shape = CircleShape
                        )
                        .clip(CircleShape)
                        .background(Color(0x4DCCCCCC))
                        .padding(20.dp)
                ) {
                    Text(
                        text = if (homeTripModel.isFraud) "Hasil Prediksi untuk ${homeTripModel.title} menggunakan TensorFlow Lite: Terdeteksi perilaku yang mencurigakan.\n" else "Hasil Prediksi untuk ${homeTripModel.title} menggunakan TensorFlow Lite: Tidak terdeteksi perilaku yang mencurigakan.\n",
                        fontFamily = AlegreyaFontFamily,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        lineHeight = 24.sp
                    )
                }


            }


        }
    }


}

val keyword: List<String> =
    listOf("pungli", "pungutan li4r", "memalak", "parkir liar", "uang tambahan")
var museumMpuMappedReview: List<String> = museumMpuReview.map { it.review }
var museumMappedReview: List<String> = museumReview.map { it.review }
var rainbowMappedReview: List<String> = rainbowReview.map { it.review }
var hutanMappedReview: List<String> = hutanReview.map { it.review }
var kampungMappedReview: List<String> = kampungReview.map { it.review }

val tripListing = listOf<HomeTripModel>(
    HomeTripModel(
        "https://drive.google.com/uc?id=1aL4a0XoyxdG4VzI2_rpkyBkjCyo986Pb",
        "Museum Mpu Tantular ",
        "Surabaya",
        4.8f,
        "Budaya",
        "Museum Negeri Mpu Tantular adalah sebuah museum negeri yang berlokasi di kecamatan Buduran, Sidoarjo, Jawa Timur.Awalnya, museum ini bernama Stedelijk Historisch Museum Soerabaia, didirikan oleh Godfried von Faber pada tahun 1933 dan diresmikan pada tanggal 25 Juli 1937. Saat ini, museum ini dikelola oleh Unit Pelaksana Teknis pada Departemen Kebudayaan dan Pariwisata.",
        museumMpuReview,
        keyword.any { it in museumMpuMappedReview }
    ),

    HomeTripModel(
        "https://drive.google.com/uc?id=1mcYdrdwn5x7Z-IT2VtQQBShFAbP63cgt",
        "Hutan Kota Srengsreng ",
        "Jakarta",
        4.8f,
        "Taman Hiburan",
        "Selain Taman Hutan Mangrove dan Pantai Indah Kapuk, salah satu taman yang sudah cukup terkenal lama bisa dijumpai di sekitar Universitas Indonesia Depok, letaknya di pinggiran Jakarta, namanya Hutan Kota Srengseng. Sesuai namanya, kawasan wisata ini terletak di daerah Srengseng Kembangan Jakbar, letaknya dekat dengan daerah Kemayoran, Kebon Jeruk serta Rawa Buaya. Hutan Srengseng ini menjadi salah satu kawasan alam hijua yang bisa dijumpai di Daerah Khusus Ibukota Jakarta.",
        hutanReview,
        keyword.any { it in hutanMappedReview }
    ),

    HomeTripModel(
        "https://drive.google.com/uc?id=1G-AXhHtMhVNv3PANdCoB7lUqDCT7xpKF",
        "Kampung Wisata Kadipaten",
        "Yogyakarta",
        4.8f,
        "Budaya",
        "Kampung Wisata Kadipaten secaara kewilayahan berada di Kelurahan Kadipaten Kecamatan Kraton. Keberadaannya juga berfungsi sebagai penyangga obyek wisata Kraton Kasultanan Yogyakarta. Sesuai dengan potensi yang ada Kampung Wisata Kadipaten kemudian mengangkat tema â€œArt and Heritage Turismâ€\u009D sebagai brandinngnya. Kawasan kampung wisata ini menjadi sangat unik dan spesifik karena disana banyak terdapat bangunan situs cagar busaya terutama bangunan Dalem Pangeran.",
        kampungReview,
        keyword.any { it in kampungMappedReview }


    ),

    HomeTripModel(
        "https://drive.google.com/uc?id=1dNNc-jhmIqYG0zrKouI0n7zGk-9p4PeV",
        "Rainbow Garden ",
        "Bandung",
        4.8f,
        "Cagar Alam",
        "Rainbow Garden Harapan Indah salah satu taman rekreasi yang terutama cocok untuk keluarga. Rainbow Garden Bekasi memadukan arena permainan anak dengan wisata kuliner. Berbagai wahana dan aktivitas tersedia di taman wisata penuh warna seluas 3000 m2 ini.",
        rainbowReview,
        keyword.any { it in rainbowMappedReview }


    ),

    HomeTripModel(
        "https://drive.google.com/uc?id=1eJSawQcwf6vOj9QXT8yvsxEioBJZqstU",
        "Museum Kereta Ambarawa",
        "Semarang",
        4.8f,
        "Budaya",
        "Museum Kereta Api Ambarawa (bahasa Inggris: Indonesian Railway Museum, Ambarawa) adalah sebuah stasiun kereta api yang sudah dialihfungsikan menjadi sebuah museum serta merupakan museum perkeretaapian pertama di Indonesia. Museum ini memiliki koleksi kereta api yang pernah berjaya pada zamannya. Museum ini secara administratif berada di Desa Panjang, Ambarawa, Semarang.\n",
        museumReview,
        keyword.any { it in museumMappedReview }


    ),


    )