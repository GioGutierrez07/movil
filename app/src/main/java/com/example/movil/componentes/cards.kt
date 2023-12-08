package com.example.movil.componentes



import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.textInputServiceFactory

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType.Companion.Uri
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.movil.R
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.ScannerViewModel
import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.ModalModificar

import dagger.Provides


@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardMain(
    id: String,
    fotosViewModel: FotosViewModel,
    navController: NavController,
     camara:ScannerViewModel,
    viewModel: TareasViewModel,
    nombre: String,
    fecha: String,
    descripcion: String,
    tipo: String,
    videoUris:String,
    imagenUri:String,
    audio: ByteArray?,
    onclickMostrarMas:()->Unit,
    mostrarMAs: Boolean,
    cardMaxWhi: Modifier= Modifier
        .padding(horizontal = 16.dp, vertical = 16.dp)
        .fillMaxWidth(),
    onclick: () -> Unit,

){

    val expandedState = remember { mutableStateOf(false) }

    val cardHeight: Dp by animateDpAsState(
        if (expandedState.value) 300.dp else 130.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
            modifier = Modifier
                .padding(10.dp)
                .height(cardHeight)
                .clickable { onclick() },
        ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = cardMaxWhi

        ) {
            Column(

            ) {

                Image(painter = rememberAsyncImagePainter(viewModel.retornaPrimeroListaUri(imagenUri))
                    , contentDescription = "",
                    Modifier
                        .width(100.dp)
                        .height(100.dp))
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
                icono = R.drawable.flechaabajo
            )

        }

        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth()
        ) {
            Multimedia(imagenUri,videoUris,fotosViewModel,navController = navController, viewModel = viewModel, camara = camara)
            SpaceAncho(2.dp)
            PlayButton(audioBytes = audio, viewModele =viewModel )
        }
        SpaceAlto(10.dp)


       }
    }



@Composable
fun TextRow(texto: String,
            color: androidx.compose.ui.graphics.Color,
            modifier:Modifier=Modifier.fillMaxWidth()){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .fillMaxWidth(),
    ) {
        Text(text = texto,
              fontSize = 16.sp,
              fontFamily = FontFamily(Font(R.font.press_start_2p)),
              modifier = modifier,
            color=color

        )

    }
}

@Composable
fun CardInformacion(
    fecha: String,
    descripcion: String,
    tipo: String,
    onclickMostrarMas:()->Unit,
    cardMaxWhi: Modifier= Modifier
        .padding(horizontal = 26.dp, vertical = 16.dp)
        .fillMaxWidth(),
    onclick: () -> Unit,
    ){

    val expandedState = remember { mutableStateOf(false) }

    val cardHeight: Dp by animateDpAsState(
        if (expandedState.value) 350.dp else 100.dp,
        animationSpec = tween(durationMillis = 500)
    )

    Card(
        modifier = Modifier
            .padding(10.dp)
            .height(cardHeight)
            .clickable { onclick() },
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = cardMaxWhi

        ) {



        }
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .fillMaxWidth(),
        ) {
            IconoPersonalizable(
                onClick = {
                    expandedState.value = !expandedState.value
                    onclickMostrarMas()
                },
                icono = R.drawable.flechaabajo
            )
            Text(text = tipo)
        }
        SpaceAlto(30.dp)

        TextRow(texto = "Fecha Limite:",MaterialTheme.colorScheme.secondary, Modifier
            .padding(horizontal = 26.dp))
        SpaceAlto(20.dp)

        TextRow(texto = fecha,MaterialTheme.colorScheme.primary, Modifier
            .padding(horizontal = 26.dp))
        SpaceAlto(20.dp)

        TextRow(texto = "Descripcion:",MaterialTheme.colorScheme.secondary, Modifier
            .padding(horizontal = 26.dp) )
        SpaceAlto(20.dp)

        TextRow(texto = descripcion,MaterialTheme.colorScheme.primary, Modifier
            .padding(horizontal = 26.dp))

    }
}








