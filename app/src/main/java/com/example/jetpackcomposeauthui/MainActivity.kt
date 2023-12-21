package com.example.jetpackcomposeauthui

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeauthui.ui.theme.JetpackComposeAuthUITheme
import com.example.travelapp.ui.features.HomeScreen
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.MappedByteBuffer
import java.nio.channels.FileChannel


class MainActivity : ComponentActivity() {
    var tflite: Interpreter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        try {
//            tflite = Interpreter(loadModelFile()!!)
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//        }
        var am = this.assets
        var hutanStream = am.open("reviews1 - hutan kota.csv")
        var kampungStream = am.open("reviews1 - kampung.csv")
        var museumStream = am.open("reviews1 - museum.csv")
        var mpuStream = am.open("reviews1 - museum mpu.csv")
        var rainbowStream = am.open("reviews1 - rainbow.csv")


        hutanReview = readCsv(hutanStream)
        kampungReview = readCsv(kampungStream)
        museumReview = readCsv(museumStream)
        museumMpuReview = readCsv(mpuStream)
        rainbowReview = readCsv(rainbowStream)



//        doInference(mappedReview, tflite)
        setContent {
            JetpackComposeAuthUITheme {
                /// Let just add navigation so users can go from one screen to another
                NavigationView()
            }
        }
    }

    @Throws(IOException::class)
    private fun loadModelFile(): MappedByteBuffer? {
        val fileDescriptor: AssetFileDescriptor = assets.openFd("model.tflite")
        val inputStream = FileInputStream(fileDescriptor.fileDescriptor)
        val fileChannel = inputStream.channel
        val startOffset = fileDescriptor.startOffset
        val declareLength = fileDescriptor.declaredLength
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declareLength)
    }

    private fun doInference(inputString: List<String>, tflite: Interpreter): Float {
//        val inputVal = FloatArray(1)
//        inputVal[0] = inputString.toFloat()
        val output = Array(1) { FloatArray(1) }
        tflite.run(inputString, output)
        return output[0][0]
    }
}

var hutanReview: List<DestinationReviewModel> = emptyList()
var kampungReview: List<DestinationReviewModel> = emptyList()
var museumReview: List<DestinationReviewModel> = emptyList()
var museumMpuReview: List<DestinationReviewModel> = emptyList()
var rainbowReview: List<DestinationReviewModel> = emptyList()

fun readCsv(inputStream: InputStream): List<DestinationReviewModel> {
    val reader = inputStream.bufferedReader()
    val header = reader.readLine()
    return reader.lineSequence()
        .filter { it.isNotBlank() }
        .map {
            val (user, destinationName, rate, review) = it.split(',', ignoreCase = false)
            DestinationReviewModel(user, rate.toInt(), review)
        }.toList()
}


@Composable
fun NavigationView() {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        // also pass navController to each screen so we can use navController in there
        composable("welcome") { WelcomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("dashboard") { HomeScreen(navController) }
        composable("detail") {
            val userObject: Int? = navController.previousBackStackEntry?.savedStateHandle?.get("index")
            if (userObject != null) {
                Detail(navController, userObject)
            }
        }
        Composable("ulasan"){ Ulasan(destinationReviewModel = , navController = )}

    }

}


data class HomeTripModel(
    val image: String,
    val title: String,
    val location: String,
    val rating: Float,
    val jenisWisata: String,
    val description: String,
    val reviewList: List<DestinationReviewModel>,
    val isFraud: Boolean
)

data class DestinationReviewModel(
    val user: String,
    val rate: Int,
    val review: String,
)

