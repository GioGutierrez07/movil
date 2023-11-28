package com.example.movil.componentes

import android.app.Activity
import android.content.ContentResolver
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun MultimediaPickerExample() {
    // Estado para almacenar la URI del archivo multimedia seleccionado
    val multimediaUri = remember { mutableStateOf<Uri?>(null) }
    val multimediaBitmap = remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current

    // Inicializar el ActivityResultLauncher
    val multimediaPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // Obtener la URI del archivo seleccionado
            multimediaUri.value = result.data?.data

            // Cargar la imagen como Bitmap
            multimediaUri.value?.let { uri ->
                multimediaBitmap.value = loadBitmap(context.contentResolver, uri)
            }
        }
    }

    Column {
        Button(onClick = {
            // Abrir el selector de archivos multimedia
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/* video/*" // Para seleccionar imágenes y videos
            multimediaPickerLauncher.launch(intent)
        }) {
            Icon(
                Icons.Filled.Home, contentDescription = "select image",
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Elegir")
        }

        // Mostrar la imagen seleccionada
        multimediaBitmap.value?.let { bitmap ->
            Image(
                bitmap = bitmap.asImageBitmap(),
                contentDescription = "Imagen seleccionada",
                modifier = Modifier.size(200.dp).padding(8.dp) //Se le da tamaño a la imagen
            )
        }
    }
}

// Función para cargar una imagen desde la URI y convertirla a un Bitmap
private fun loadBitmap(contentResolver: ContentResolver, uri: Uri): Bitmap {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        ImageDecoder.decodeBitmap(ImageDecoder.createSource(contentResolver, uri))
    } else {
        MediaStore.Images.Media.getBitmap(contentResolver, uri)
    }
}