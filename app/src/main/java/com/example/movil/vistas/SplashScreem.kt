package com.example.movil.vistas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.movil.R
import kotlinx.coroutines.delay

@Composable
fun LottieAnimaciiones(modifier:Modifier , imagen:Int) {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(imagen))
    
    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever ,
        modifier = modifier)

}

@Composable
fun SplashScreem(navController: NavController){

    LaunchedEffect(key1 = true) {
        delay(2000)
        navController.navigate("Home"){
            popUpTo(0)
        }
    }

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        LottieAnimaciiones(modifier = Modifier.fillMaxWidth(), imagen =R.raw.animation)

    }
    

}