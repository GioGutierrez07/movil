package com.example.movil

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.movil.ui.theme.Purple40


@Composable
fun SelectorMultimedia(
    seleccion: String,
    onSeleccionCambiada: (String) -> Unit
) {
    var iconoSeleccionado by remember { mutableStateOf(seleccion == "Fotos") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconoSeleccion(
            seleccionado = iconoSeleccionado,
            iconoResId = R.drawable.fotos, // Cambia el icono
            texto = "FOTOS",
            onClick = {
                iconoSeleccionado=true
                onSeleccionCambiada("Fotos")
            }
        )

          Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "MULTIMEDIA",
                textAlign = TextAlign.Center,
                color= Purple40,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,

            )

        Spacer(modifier = Modifier.width(16.dp))

        IconoSeleccion(
            seleccionado = !iconoSeleccionado,
            iconoResId = R.drawable.audio, // Cambia el icono
            texto = "AUDIOS",
            onClick = {
                iconoSeleccionado=false
                onSeleccionCambiada("Audio")
            }
        )
    }
}


@Preview
@Composable
fun SelectorMultimediaPreview() {
    var seleccion by remember { mutableStateOf("") }

    SelectorMultimedia(
        seleccion = seleccion,
        onSeleccionCambiada = { nuevaSeleccion ->
            seleccion = nuevaSeleccion
        }
    )
}