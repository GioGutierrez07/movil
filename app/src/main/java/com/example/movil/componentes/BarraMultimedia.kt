package com.example.notastareas.componentes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movil.R

import com.example.notastareas.viewModels.TareasViewModel

@Composable
fun SelectorMultimedia(
    viewModel: TareasViewModel
) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconoSeleccion(
            seleccionado = viewModel.estado.fotos,
            iconoResId = R.drawable.fotos, // Cambia el icono
            texto = "FOTOS",
            onClick = {
                viewModel.esFoto()
            }
        )

       SpaceAncho()
        Text(
            text = "MULTIMEDIA",
            textAlign = TextAlign.Center,
            color= MaterialTheme.colorScheme.primary,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            style = MaterialTheme.typography.displaySmall,

            )

        SpaceAncho()

        IconoSeleccion(
            seleccionado = viewModel.estado.audios,
            iconoResId = R.drawable.audio, // Cambia el icono
            texto = "AUDIOS",
            onClick = {
                viewModel.esAudio()
            }
        )
    }
}