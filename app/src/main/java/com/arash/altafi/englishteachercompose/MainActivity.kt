package com.arash.altafi.englishteachercompose

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arash.altafi.englishteachercompose.ui.theme.EnglishTeacherComposeTheme
import com.arash.altafi.englishteachercompose.utils.language.LocaleUtils
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocaleUtils.setLocale(this)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        FirebaseApp.initializeApp(this)

        // Now retrieve the FCM token
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val token = task.result
                Log.d("FCMToken", "FCM Token: $token")
                // Store the token if needed
            } else {
                Log.w("FCMToken", "Failed to retrieve FCM token", task.exception)
            }
        }

        FirebaseMessaging.getInstance().subscribeToTopic("all")

        enableEdgeToEdge()
        setContent {
            EnglishTeacherComposeTheme {
                AppTheme()
            }
        }
    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleUtils.setLocale(newBase))
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        LocaleUtils.setLocale(this)
    }
}