package com.example.movil.componentes

import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.core.app.ActivityCompat
import android.Manifest
import android.app.Activity
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun CameraButtonExample() {
    val context = LocalContext.current
    // Estado para almacenar la imagen capturada
    var capturedImage by remember { mutableStateOf<ImageBitmap?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        // Manejo del bitmap de la imagen capturada
        // Por ejemplo, mostrarlo en la UI o guardarlo en el almacenamiento

        // Manejo del bitmap de la imagen capturada
        if (bitmap != null) {
            capturedImage = bitmap.asImageBitmap()
        }
    }

    Column {
        Button(onClick = {
            if (hasCameraPermission(context)) {
                launcher.launch(null)
            } else {
                // Solicitar permiso
                requestCameraPermission(context)
            }
        }) {
            Text("Abrir Cámara")
        }

        // Mostrar la imagen capturada si está disponible
        capturedImage?.let { image ->
            Image(
                bitmap = image,
                contentDescription = "Imagen Capturada"
            )
        }
    }
}

fun hasCameraPermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED
}

fun requestCameraPermission(activity: Context) {
    ActivityCompat.requestPermissions(
        activity as Activity,
        arrayOf(Manifest.permission.CAMERA),
        PERMISSION_CAMERA_CODE
    )
}


const val PERMISSION_CAMERA_CODE = 101