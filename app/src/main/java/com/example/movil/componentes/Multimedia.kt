package com.example.movil.componentes

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext

@Composable
fun MultimediaPickerExample() {
    // Estado para almacenar la URI del archivo multimedia seleccionado
    val multimediaUri = remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current

    // Inicializar el ActivityResultLauncher
    val multimediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Obtener la URI del archivo seleccionado
            multimediaUri.value = result.data?.data
        }
    }

    Column {
        Button(onClick = {
            // Abrir el selector de archivos multimedia
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*" // Para seleccionar imágenes y videos
            multimediaPickerLauncher.launch(intent)
        }) {
            Text(text = "Seleccionar Multimedia")
        }

        // Mostrar la URI del archivo seleccionado
        multimediaUri.value?.let { uri ->
            Text(text = "Archivo seleccionado: $uri")
        }
    }
}