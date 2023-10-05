package com.example.movil

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldPersonalizado(
    texto: String,
    onTextoCambiado: (String) -> Unit,
    etiqueta: String,
    placeholder: String,
    icono: ImageVector? = null,
    multiLinea: Boolean
) {
    OutlinedTextField(
        value = texto,
        onValueChange = { nuevoTexto ->
            onTextoCambiado(nuevoTexto)
        },
        label = { Text(etiqueta) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = { icono?.let { Icon(imageVector = icono, contentDescription = null) } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = androidx.compose.ui.text.input.ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                // Acción realizada al presionar "Done" en el teclado
            }
        ),
        singleLine = multiLinea, // Permite múltiples líneas
        maxLines = Int.MAX_VALUE // Permite múltiples líneas
    )
}
@Preview
@Composable
fun TextFieldPersonalizadoPreview() {
    var texto by remember { mutableStateOf("") }
    TextFieldPersonalizado(
        texto = texto,
        onTextoCambiado = { nuevoTexto -> texto = nuevoTexto
        },
        etiqueta = stringResource(id = R.string.labelTarea),
        placeholder = stringResource(id = R.string.placeholderTarea),
        icono =  Icons.Default.Create ,//  cambiar el icono aquí
        multiLinea= true)
}