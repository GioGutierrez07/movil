package com.example.movil.componentes


import android.graphics.Bitmap
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Button
import androidx.compose.material3.Card

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ComposeCompilerApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign

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
    imagen:ImageBitmap?,
    onclickMostrarMas:()->Unit,
    mostrarMAs: Boolean,
    cardMaxWhi: Modifier=Modifier.padding(horizontal = 16.dp, vertical = 16.dp).fillMaxWidth(),
    onclick: () -> Unit,

){

    val expandedState = remember { mutableStateOf(false) }
    val cardHeight: Dp by animateDpAsState(
        if (expandedState.value) 400.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
            modifier = Modifier.padding(10.dp).height(cardHeight)
            .clickable { onclick ()},
        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = cardMaxWhi

        ) {
            Column(

            ) {

                imagen?.let { image ->
                    Image(
                        bitmap = image,
                        contentDescription = "Imagen Capturada",
                        modifier = Modifier.height(100.dp)
                            .width(100.dp)
                            .clip(CircleShape)
                    )
                }

            }

            Text(
                text = tipo,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            IconoPersonalizable(
                onClick = {
                    expandedState.value = !expandedState.value
                            onclickMostrarMas()
                          },
                icono = R.drawable.add
            )

        }
        if(mostrarMAs){

           TextRow(texto = nombre, cardMaxWhi )
           TextRow(texto = fecha, cardMaxWhi)
           TextRow(texto = descripcion, cardMaxWhi)


           SpaceAlto()
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier.padding(horizontal = 70.dp)
            ) {
                /*
                Button(
                    onClick = { /* */ },
                    modifier=Modifier.padding(horizontal = 10.dp) ) {
                    Text("Editar")
                }
                */
                Spacer(Modifier.width(8.dp))

                SpaceAbajo()
            }
       }
    }

}

@Composable
fun TextRow(texto: String ,modifier: Modifier=Modifier.fillMaxWidth()){
    Row(
        modifier =modifier
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
        imagen = null,
        {},
        mostrarMAs = false,
        cardMaxWhi=Modifier.padding(horizontal = 16.dp, vertical = 16.dp).fillMaxWidth(),
        {}
    )
}



