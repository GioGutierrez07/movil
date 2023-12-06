package com.example.movil.notificaciones

import android.Manifest
import android.app.AlarmManager
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.example.movil.MainActivity
import com.example.movil.R
import com.example.movil.notificaciones.Notification.Constants.CHANEL_ID
import okhttp3.internal.notify
import java.util.Calendar
import java.util.stream.DoubleStream.builder



class Notification : BroadcastReceiver() {
    companion object{
        const val NOTIFICATION_ID = 5
        const val MY_CHANNEL_ID="myCanal"
    }

    override fun onReceive(context: Context, p1: Intent?) {
        ///creamos una funcion para lanzar lanotificacion
        createSimpleNoti(context)
    }

    object  Constants{
        const val CHANEL_ID= "NOTAS"
    }

    fun createSimpleNoti(context: Context){

        val intent = Intent(context,MainActivity::class.java).apply {
            flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val flag =if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE  else 0
        val pendingIntent= PendingIntent.getActivity(context,0, intent ,flag)

        var notoficacion= NotificationCompat.Builder(context, com.example.movil.notificaciones.Notification.MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.astronauta)
            .setContentTitle("Notificacion")
            .setContentText("Es una noti")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Tiene una tarea pendiente")
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE)  as NotificationManager
        manager.notify(NOTIFICATION_ID,notoficacion)


    }





}