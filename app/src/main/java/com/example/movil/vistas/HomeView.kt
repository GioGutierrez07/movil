package com.example.notastareas.views

import RegistrarTareaViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movil.R
import com.example.notastareas.componentes.BotonFlotante
import com.example.notastareas.componentes.CardMain
import com.example.notastareas.componentes.SpaceAlto
import com.example.notastareas.componentes.TextFieldPersonalizado

import com.example.notastareas.viewModels.TareasViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    bDModel:RegistrarTareaViewModel
    ,viewModel: TareasViewModel,
    navController: NavController){
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Notas Y tareas",color= MaterialTheme.colorScheme.onPrimary) },
                colors= TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

            )
        },
        floatingActionButton = {
            BotonFlotante(
                contenedorColor = Color.Gray,
                colorContenido = Color.White,
            ){
                navController.navigate("Formulario")
            }
        }
    ) {

        ContenidoHome(it,bDModel,navController)
    }
}


@Composable
fun ContenidoHome(paddingValues: PaddingValues,
                  bDModel:RegistrarTareaViewModel,
                  navController: NavController){

    Column(
        modifier= Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TextFieldPersonalizado(
            texto = "",
            onTextoCambiado = { /*viewModel.onValue(it,"nombre")*/ },
            etiqueta = stringResource(id = R.string.labelBuscar),
            placeholder = stringResource(id = R.string.placeholderBuscar),
            icono =  Icons.Default.Create ,
            multiLinea = false,)//  cambiar el icono aquí

        SpaceAlto()
        val actividalesList by bDModel.notasList.collectAsState()

        LazyColumn {
            items(actividalesList){item->

                //eliminar elemento
                val eliminar= SwipeAction(
                    icon= rememberVectorPainter(Icons.Default.Delete ),
                    background = Color.Red,
                    onSwipe = {bDModel.deleteNota(item)}
                )
                //endActions para eliminar de izquierda a derecha starActions de derecha a
                SwipeableActionsBox(endActions = listOf(eliminar), swipeThreshold = 100.dp) {
                    CardMain(
                        id = item.id.toString(),
                        nombre = item.nombre ,
                        fecha = item.fecha,
                        descripcion =item.descripcion ,
                        tipo = item.tipo ,
                        onclickMostrarMas = { bDModel.cambiarMostrar() },
                        mostrarMAs = bDModel.mostrarMas
                    ){
                        navController.navigate("Editar/${item.id}")
                    }

                }



            }
        }

    }

}


