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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun SelectorTareaNotas(
    seleccion: String,
    onSeleccionCambiada: (String) -> Unit
) {
    var tareaSeleccionada by remember { mutableStateOf(seleccion == "Tarea") }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconoSeleccion(
            seleccionado = tareaSeleccionada,
            iconoResId = R.drawable.tarea, // Cambia el icono de tarea aquí
            texto = "Tarea",
            onClick = {
                tareaSeleccionada = true
                onSeleccionCambiada("Tarea")
            }
        )
        Spacer(modifier = Modifier.width(16.dp))
        IconoSeleccion(
            seleccionado = !tareaSeleccionada,
            iconoResId = R.drawable.nota, // Cambia el icono de notas aquí
            texto = "Notas",
            onClick = {
                tareaSeleccionada = false
                onSeleccionCambiada("Notas")
            }
        )
    }
}

@Composable
fun IconoSeleccion(
    seleccionado: Boolean,
    iconoResId: Int,
    texto: String,
    onClick: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {
        Icon(
            painter = painterResource(id = iconoResId),
            contentDescription = null,
            modifier = Modifier.size(48.dp),
            tint = if (seleccionado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = texto,
            color = if (seleccionado) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
        )
    }
}
@Preview
@Composable
fun SelectorTarea2NotasPreview() {
    var seleccion by remember { mutableStateOf("Tarea") }

    SelectorTareaNotas(
        seleccion = seleccion,
        onSeleccionCambiada = { nuevaSeleccion ->
            seleccion = nuevaSeleccion
        }
    )
}