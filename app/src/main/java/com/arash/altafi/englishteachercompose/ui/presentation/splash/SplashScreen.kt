package com.arash.altafi.englishteachercompose.ui.presentation.splash

import androidx.compose.foundation.MarqueeAnimationMode
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import kotlinx.coroutines.delay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Alignment
import androidx.navigation.NavController
import com.arash.altafi.englishteachercompose.utils.Constant

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(5000)
        navController.navigate(Constant.Routes.LEARN) {
            popUpTo(Constant.Routes.SPLASH) {
                inclusive = true
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier
                .width(200.dp)
                .basicMarquee(animationMode = MarqueeAnimationMode.Immediately),
            text = "Splash Screen",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )
    }
}