package com.example.notastareas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconoSeleccion(
    seleccionado: Boolean,
    iconoResId: Int,
    texto: String,
    onClick: (Boolean) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        IconButton(onClick = { onClick(!seleccionado)}) {
            Icon(
                painter = painterResource(id = iconoResId),
                contentDescription = null,
                modifier =
                Modifier
                    .size(60.dp),
                tint = if (seleccionado) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.inverseSurface
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = texto,
            color = if (seleccionado) MaterialTheme.colorScheme.inversePrimary else MaterialTheme.colorScheme.inverseSurface
        )
    }
}

@Composable
fun IconoPersonalizable(
    onClick: () -> Unit,
    tamañoIcono: Int = 48,
    colorIcono: Color = Color.White,
    colorFondo: Color = MaterialTheme.colorScheme.primary,
    icono: Int
) {
    Box(
        modifier = Modifier
            .size(tamañoIcono.dp)
            .clickable { onClick() }
            .background(color = colorFondo, shape = CircleShape)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter =  painterResource(id = icono), // Puedes cambiar el icono aquí
            contentDescription = "Agregar",
            tint = colorIcono,
            modifier = Modifier.size(tamañoIcono.dp)
        )
    }
}


