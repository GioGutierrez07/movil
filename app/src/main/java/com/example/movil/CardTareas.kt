package com.example.movil

import com.example.movil.ui.theme.MovilTheme


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun tareaCard(
    nombre: String,
    descripcion: String, imagen: Int,
    modifier: Modifier = Modifier
) {
    var showDescription by remember { mutableStateOf(false) }

    Card(
        modifier= Modifier
            .padding(vertical = 4.dp, horizontal = 8.dp)
            .fillMaxWidth(),

        ) {
        Column() {
            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = imagen),
                    contentDescription = nombre,
                    modifier = Modifier.size(100.dp)
                )

                Text(
                    text = nombre,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "Mostrar descripción",
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Top) // Mover el icono a la esquina superior derecha
                        .padding(
                            end = 16.dp,
                            top = 8.dp
                        ) // Agregar margen en la parte superior y derecha del icono
                        .clickable { showDescription = !showDescription }
                )
            }
            if (showDescription) {
                Text(
                    color= MaterialTheme.colorScheme.primary,
                    text = descripcion,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MovilTheme {
        tareaCard(nombre = "hacer tarea", descripcion = "Debo hacer la tarea de movil", imagen = R.drawable.astronauta  )

    }
}
