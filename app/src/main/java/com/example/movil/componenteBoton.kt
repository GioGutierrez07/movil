package com.example.movil

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BotonPersonalizado(
    texto: String,
    onClick: () -> Unit,
    colorFondo: Color = Color.Black,
    colorTexto: Color = Color.White
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = colorFondo,
            contentColor = colorTexto
        )
    ) {
        Text(text = texto)
    }
}

@Preview
@Composable
fun BotonPersonalizadoPreview() {
    var contador by remember { mutableStateOf(0) }

    BotonPersonalizado(
        texto = stringResource(id = R.string.botonRegistrar),
        onClick = { },
        colorFondo = Color.Green,
        colorTexto = Color.White
    )
}
