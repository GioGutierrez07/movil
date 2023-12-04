package com.example.movil



import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass

import androidx.compose.ui.Modifier

import com.example.movil.navegacion.NavManager
import com.example.movil.notificaciones.Notification
import com.example.movil.notificaciones.Notification.Companion.NOTIFICATION_ID
import com.example.movil.ui.theme.MovilTheme
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.RegistrarTareasViewModel
import com.example.movil.viewModels.ScannerViewModel

import com.example.movil.viewModels.TareasViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar


@AndroidEntryPoint  //indica que toda la ativity utilizara dagger y hilt
class MainActivity : ComponentActivity() {

    companion object {
        const val MY_CHANNEL_ID = "myChannel"
    }

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        val myNotificationButton = findViewById<Button>(R.id.btnNotification)
        createChannel()
        myNotificationButton.setOnClickListener {
            scheduleNotification()
        }
         */
        createChannel()

        val fotosViewModel:FotosViewModel by viewModels()
        val camaraView: ScannerViewModel by viewModels()
        val tareaViewM: RegistrarTareasViewModel by viewModels()
        val estadoTarea: TareasViewModel by viewModels()

        setContent {

            MovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val windowSize = calculateWindowSizeClass(this)
                    //para vista previa WindowWidthSizeClass.Compact,
                    NavManager(
                        fotosViewModel,
                        camaraView,
                        bDModel =tareaViewM ,
                        viewModel = estadoTarea,
                        windowSize = windowSize.widthSizeClass)
                }
            }
        }
    }

    private fun scheduleNotification() {
        val intent = Intent(applicationContext, Notification::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, Calendar.getInstance().timeInMillis + 15000, pendingIntent)
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                MY_CHANNEL_ID,
                "MySuperChannel",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "hola"
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }
    }

}


