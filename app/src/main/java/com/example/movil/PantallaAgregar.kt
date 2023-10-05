package com.example.movil

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.myapplication.IconoAgregar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movil.BotonPersonalizado
import com.example.movil.R
import com.example.movil.TextFieldPersonalizado
import com.example.movil.tareaCard

@Composable
fun PrubaPanatllaAgregar(
    onAddClick: () -> Unit,
    cardContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        var seleccion by remember { mutableStateOf("Tarea") }
        //selecionar tarea o notas
        SelectorTareaNotas(
            seleccion = seleccion,
            onSeleccionCambiada = { nuevaSeleccion ->
                seleccion = nuevaSeleccion
            }
        )
        //ingreasr nombre de la tarea
        TextFieldPersonalizado(
            texto ="",
            onTextoCambiado = { },
            etiqueta = stringResource(id = R.string.labelTarea),
            placeholder = stringResource(id = R.string.placeholderTarea),
            icono =  Icons.Default.Create ,
            multiLinea = false)//  cambiar el icono aquí

        //ingresar descripcion de la tarea
        TextFieldPersonalizado(
            texto = "",
            onTextoCambiado = { },
            etiqueta = stringResource(id = R.string.labelDescripcion),
            placeholder = stringResource(id = R.string.placeholderDescripcion),
            icono =  Icons.Default.Create ,
            multiLinea = false)//  cambiar el icono aquí


        BotonPersonalizado(
            texto = stringResource(id = R.string.botonRegistrar),
            onClick = { },

            colorFondo = Color.Green,
            colorTexto = Color.White
        )


        // Espacio en blanco para separar el contenido del botón de agregar
        Spacer(modifier = Modifier.height(16.dp))

        // Botón de agregar en la parte inferior
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            IconoPersonalizable(
                onClick = { /*TODO*/ },
                icono = R.drawable.regresar)
        }
    }
}

@Composable
fun IconoPersonalizable(
    onClick: () -> Unit,
    tamañoIcono: Int = 48,
    colorIcono: Color = Color.White,
    colorFondo: Color = MaterialTheme.colorScheme.onBackground,
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

@Preview
@Composable
fun PantallaAddPreview() {
    PrubaPanatllaAgregar(
        onAddClick = { /* Acción al hacer clic en Agregar */ }
    ) {

    }
}