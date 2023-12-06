package com.example.movil.viewModels

import android.app.Notification
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiverNotificacion : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val notification = intent?.getParcelableExtra<Notification>("EXTRA_NOTIFICATION")
        val notificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(1, notification)
    }



}
