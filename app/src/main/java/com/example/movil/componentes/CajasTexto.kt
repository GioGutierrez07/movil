package com.example.movil.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
        onValueChange =  onTextoCambiado,
        label = { Text(etiqueta) },
        placeholder = { Text(text = placeholder) },
        leadingIcon = { icono?.let { Icon(imageVector = icono, contentDescription = null) } },
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTextFieldPersonalizado(value: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp)
    )
}