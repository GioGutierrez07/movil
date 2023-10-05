package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconoAgregar(
    onClick: () -> Unit,
    tamañoIcono: Int = 48,
    colorIcono: Color = Color.White,
    colorFondo: Color = Color.Blue
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
            imageVector = Icons.Default.Add, // Puedes cambiar el icono aquí
            contentDescription = "Agregar",
            tint = colorIcono,
            modifier = Modifier.size(tamañoIcono.dp)
        )
    }
}

@Preview
@Composable
fun IconoAgregarPreview() {
    IconoAgregar(
        onClick = { /* Acción al hacer clic */ },
        tamañoIcono = 48,
        colorIcono = Color.White,
        colorFondo = Color.Gray
    )
}