package com.arash.altafi.englishteachercompose.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.arash.altafi.englishteachercompose.ui.presentation.dictionary.DictionaryScreen
import com.arash.altafi.englishteachercompose.ui.presentation.learn.LearnScreen
import com.arash.altafi.englishteachercompose.ui.presentation.login.LoginScreen
import com.arash.altafi.englishteachercompose.ui.presentation.pronunciation.PronunciationScreen
import com.arash.altafi.englishteachercompose.ui.presentation.register.RegisterScreen
import com.arash.altafi.englishteachercompose.ui.presentation.setting.SettingScreen
import com.arash.altafi.englishteachercompose.ui.presentation.splash.SplashScreen
import com.arash.altafi.englishteachercompose.utils.Constant

@Composable
fun Navigation(
    navController: NavHostController,
    innerPadding: PaddingValues
) {
    NavHost(
        navController = navController,
        startDestination = Constant.Routes.SPLASH,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(
            route = Constant.Routes.LEARN
        ) {
            LearnScreen(navController)
        }

        composable(
            route = Constant.Routes.SPLASH
        ) {
            SplashScreen(navController)
        }

        navigation(
            route = Constant.CategoryRoutes.AUTH_ROUTES,
            startDestination = Constant.Routes.LOGIN
        ) {
            composable(
                route = Constant.Routes.LOGIN
            ) {
                LoginScreen(navController)
            }

            composable(
                route = Constant.Routes.REGISTER
            ) {
                RegisterScreen(navController)
            }
        }

        navigation(
            route = Constant.CategoryRoutes.APP_ROUTES,
            startDestination = Constant.Routes.LEARN
        ) {
            composable(
                route = Constant.Routes.SETTING
            ) {
                SettingScreen(navController)
            }
            composable(
                route = Constant.Routes.DICTIONARY
            ) {
                DictionaryScreen(navController)
            }
            composable(
                route = Constant.Routes.PRONUNCIATION
            ) {
                PronunciationScreen(navController)
            }
        }
    }
}