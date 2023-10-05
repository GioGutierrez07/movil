package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.movil.BotonPersonalizado
import com.example.movil.R
import com.example.movil.TextFieldPersonalizado
import com.example.movil.tareaCard

@Composable
fun compoentesPureba(
    onAddClick: () -> Unit,
    cardContent: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        TextFieldPersonalizado(
            texto ="",
            onTextoCambiado = { },
            etiqueta = stringResource(id = R.string.labelBuscar),
            placeholder = stringResource(id = R.string.placeholderBuscar),
            icono =  Icons.Default.Create ,
            multiLinea = false)//  cambiar el icono aquí

        // barraBusqueda()//barra
        cardContent()// Contenido del Card


        //var texto by remember { mutableStateOf("") }

        TextFieldPersonalizado(
            texto ="",
            onTextoCambiado = { },
            etiqueta = stringResource(id = R.string.labelTarea),
            placeholder = stringResource(id = R.string.placeholderTarea),
            icono =  Icons.Default.Create ,
            multiLinea = false)//  cambiar el icono aquí

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
            IconoAgregar(
                onClick = onAddClick,
                tamañoIcono = 48,
                colorIcono = Color.White,
                colorFondo = Color.Gray
            )
        }
    }
}

@Preview
@Composable
fun CardInferiorPreview() {
    compoentesPureba(
        onAddClick = { /* Acción al hacer clic en Agregar */ }
    ) {
        tareaCard(
            nombre = "hacer tarea",
            descripcion = "Debo hacer la tarea de movil",
            imagen =R.drawable.astronauta  )
    }
}
