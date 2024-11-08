package com.arash.altafi.englishteachercompose.service

import android.Manifest
import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.arash.altafi.englishteachercompose.MainActivity
import com.arash.altafi.englishteachercompose.R
import com.arash.altafi.englishteachercompose.utils.PermissionUtils
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        Log.i("FCMToken", "onNewToken: $newToken")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val context = this
        remoteMessage.notification?.let {
            val title = it.title ?: "Default Title"
            val body = it.body ?: "Default Body"
            val imageUrl = remoteMessage.data["imageUrl"]
            val url = remoteMessage.data["url"]

            // Handle notification display
            sendNotification(context, title, body, imageUrl, url)
        }
    }

    @SuppressLint("MissingPermission")
    private fun sendNotification(context: Context, title: String, body: String, imageUrl: String?, url: String?) {
        // Load image if available
        val largeIcon = imageUrl?.let {
            val urlConnection = java.net.URL(it).openConnection()
            BitmapFactory.decodeStream(urlConnection.getInputStream())
        }

        // Set up intent to open the Firebase screen in the app
        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            data = android.net.Uri.parse("https://yourapp.com/firebaseScreen")
        }
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notificationBuilder = NotificationCompat.Builder(this, "default_channel")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(body)
            .setLargeIcon(largeIcon)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        url?.let {
            // Add button to open URL
            val urlIntent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse(it))
            val urlPendingIntent = PendingIntent.getActivity(this, 1, urlIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
            notificationBuilder.addAction(R.drawable.ic_launcher_foreground, "Open Link", urlPendingIntent)
        }

        with(NotificationManagerCompat.from(this)) {
            if (PermissionUtils.isPermissionGranted(context, Manifest.permission.CAMERA)) {
                notify(System.currentTimeMillis().toInt(), notificationBuilder.build())
            }
        }
    }
}
