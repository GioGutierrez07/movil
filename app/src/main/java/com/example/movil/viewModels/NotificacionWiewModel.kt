package com.example.movil.viewModels

import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.movil.R
import com.example.movil.state.NotasEstado
import com.example.movil.vistas.NotificacionApp

class NotificacionWiewModel : ViewModel() {

    var state by mutableStateOf(NotasEstado())
        private set

    @Composable
    fun sendNotification(context: Context, fechaEjecucion: Long) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notificacionn = NotificationCompat.Builder(context, NotificacionApp.CHANNEL_ID)
            .setContentTitle(state.nombre)
            .setContentText("Descripcion")
            .setSmallIcon(R.drawable.logo_notificacion)
            .setAutoCancel(true)
            .build()

        scheduleNotification(context, notificacionn, fechaEjecucion)
    }

    private fun scheduleNotification(context: Context, notification: Notification, fechaEjecucion: Long) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmReceiverNotificacion::class.java)
        intent.putExtra("EXTRA_NOTIFICATION", notification)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            1, // Puedes usar un ID único aquí
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}