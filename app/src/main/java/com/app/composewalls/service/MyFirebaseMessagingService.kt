package com.app.composewalls.service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

private const val TAG = "MyFirebaseMessaging"

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        Log.e(TAG, "onMessageReceived: $p0")
    }

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Log.e(TAG, "onNewToken: $p0")
    }
}