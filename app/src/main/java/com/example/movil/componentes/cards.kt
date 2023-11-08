package com.example.notastareas.componentes

import android.graphics.drawable.Drawable
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.splineBasedDecay
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.movil.R

import dagger.Provides


@Composable
fun CardMain(
    id: String,
    nombre: String,
    fecha: String,
    descripcion: String,
    tipo: String,
    onclickMostrarMas:()->Unit,
    mostrarMAs: Boolean,
    onclick: () -> Unit
){
    Card(
            modifier = Modifier.padding(10.dp)
            .clickable { onclick ()},
        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .fillMaxWidth()


        ) {
            Column(

            ) {
                Image(
                    painter = painterResource(R.drawable.astronauta),
                    contentDescription = "",
                    modifier = Modifier.size(100.dp)
                )
            }

            Text(
                text = tipo,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            IconoPersonalizable(
                onClick = { onclickMostrarMas() },
                icono = R.drawable.desplegar
            )

        }
        if(mostrarMAs && id==id){

           TextRow(texto = nombre)
           TextRow(texto = fecha)
           TextRow(texto = descripcion)

           SpaceAlto()
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(horizontal = 70.dp)
            ) {
                Button(
                    onClick = { /* */ },
                    modifier=Modifier.padding(horizontal = 10.dp) ) {
                    Text("Editar")
                }
                Spacer(Modifier.width(8.dp))

                SpaceAbajo()
            }
       }
    }

}

@Composable
fun TextRow(texto: String){
    Row(
        modifier =Modifier.fillMaxWidth()
    ) {
        Text(text = texto,
              fontSize = 16.sp,
              modifier = Modifier.fillMaxWidth(),
              textAlign = TextAlign.Center
        )

        SpaceAlto()
    }
}



@Preview
@Composable
fun MainCardPre(){
    CardMain(
        id = "",
        nombre = "Tarea",
        fecha = "27/09/2023",
        descripcion ="esto es una tarea muuy complicada",
        tipo = "Tarea",
        {},
        mostrarMAs = false,
        {}
    )
}



