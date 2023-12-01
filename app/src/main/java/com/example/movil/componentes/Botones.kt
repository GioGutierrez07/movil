package com.example.movil.componentes

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

//boton flotante
@Composable
fun BotonFlotante(icono:ImageVector=Icons.Filled.Add,contenedorColor:Color ,colorContenido: Color, onclick:()->Unit){
    FloatingActionButton(onClick = onclick,
        containerColor =contenedorColor,
        contentColor = colorContenido
    ) {
        Icon(
            icono,
            contentDescription ="",
            modifier= Modifier.size(30.dp))
    }
}

@Composable
fun BotonPersonalizado(
    valido:Boolean=false,
    texto: String,
    onClick: () -> Unit,
    colorFondo: Color = Color.Black,
    colorTexto: Color = Color.White,
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
@Composable
fun MainButtonRegistrar(
    text: String,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,

) {
    OutlinedButton(
        onClick = onClick, colors = ButtonDefaults.outlinedButtonColors(
            contentColor = color,
            containerColor = Color.Transparent
        ),
        modifier = Modifier
            .height(60.dp).width(190.dp)
            .padding(horizontal = 10.dp)
    ) {
        Text(text = text)
    }
}




