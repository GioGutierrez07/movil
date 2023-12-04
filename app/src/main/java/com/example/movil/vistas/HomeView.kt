package com.example.movil.vistas

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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

import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize

import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.movil.R

import com.example.movil.componentes.BotonFlotante
import com.example.movil.componentes.CardMain

import com.example.movil.componentes.SpaceAlto
import com.example.movil.componentes.SpaceAncho
import com.example.movil.componentes.TextFieldPersonalizado
import com.example.movil.componentes.VideoGrabar
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.RegistrarTareasViewModel
import com.example.movil.viewModels.ScannerViewModel
import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.utils.ReplyNavigationType



import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotasApp(
    fotosViewModel: FotosViewModel,
    camara:ScannerViewModel,
             bDModel: RegistrarTareasViewModel,
              navController: NavController,
              windowSize: WindowWidthSizeClass,
              viewModel: TareasViewModel){

    val navigationType: ReplyNavigationType

    when(windowSize){
        WindowWidthSizeClass.Compact ->{
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ReplyNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded ->{
            navigationType = ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            navigationType = ReplyNavigationType.BOTTOM_NAVIGATION
        }
    }

    HomeView(fotosViewModel,camara,bDModel , navController , navigationType,viewModel )
    
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(
    fotosViewModel: FotosViewModel,
    camara: ScannerViewModel,
    bDModel: RegistrarTareasViewModel,
    navController: NavController,
    navigationType: ReplyNavigationType,
    viewModel: TareasViewModel
    ){

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

                if(navigationType== ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER){

                }else{
                    //ContenidoHome(it,bDModel,navController)
                    navController.navigate("Formulario")
                }


            }
        }
    ) {
        if(navigationType== ReplyNavigationType.PERMANENT_NAVIGATION_DRAWER){
            HommeTablet(fotosViewModel,camara,it,bDModel,navController, viewModel )
        }else{
            //ContenidoHome(it,bDModel,navController)
            ContenidoHome(fotosViewModel,camara,it,bDModel,navController,viewModel)
        }

    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContenidoHome(
    fotosViewModel: FotosViewModel,
    camara: ScannerViewModel,
    paddingValues: PaddingValues,
                  bDModel:RegistrarTareasViewModel,
                  navController: NavController,
                  viewModel:TareasViewModel) {

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
            multiLinea = false)//  cambiar el icono aquí

        SpaceAlto()
        val actividalesList by bDModel.notasList.collectAsState()

        FlowRow {
            actividalesList.forEach(){item->

                //eliminar elemento
                val eliminar= SwipeAction(
                    icon= rememberVectorPainter(Icons.Default.Delete ),
                    background = Color.Red,
                    onSwipe = {bDModel.deleteNota(item)}
                )
                //endActions para eliminar de izquierda a derecha starActions de derecha a
                SwipeableActionsBox(endActions = listOf(eliminar), swipeThreshold = 100.dp) {

                    val foto= viewModel.byteArrayToBitmap(item.foto)
                    val imagen=viewModel.bitmapToImageBitmap(foto)


                    CardMain(
                        id = item.id.toString(),
                        viewModel,
                        nombre = item.nombre ,
                        fecha = item.fecha,
                        descripcion =item.descripcion ,
                        tipo = item.tipo ,
                        imagen =  imagen,
                        imagenUri = item.fotoUri,
                        audio = item.audio,
                        onclickMostrarMas = { bDModel.cambiarMostrar() },
                        mostrarMAs = bDModel.mostrarMas,
                    ){
                        //navController.navigate("Editar/${item.id}")
                        viewModel.editar(true)
                        viewModel.idEstado(item.id)

                    }

                }
                if (viewModel.estado.editar) {
                    ModalModificar(camara,bDModel, viewModel, navController, id = viewModel.estado.id) { viewModel.editar(false)}

                }



            }

        }


    }

}




@Composable
fun HommeTablet(
    fotosViewModel: FotosViewModel,
    camara:ScannerViewModel,
                paddingValues: PaddingValues,
                         bDModel:RegistrarTareasViewModel,
                         navController: NavController,
                         viewModel: TareasViewModel){

    Row(
        modifier = Modifier.fillMaxHeight(),
    ) {
        // Primera columna
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.Black)
                .padding()
        ) {
            // Contenido de la primera columna
           ContenidoHomeTablet(camara,paddingValues , bDModel , navController,viewModel )
        }

        // Segunda columna
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(Color.LightGray)
                .padding()
        ) {
            // Contenido de la segunda columna
            FormularioView(fotosViewModel,camara,bDModel , viewModel , navController )
        }
    }

    }

@Composable
fun ContenidoHomeTablet(
    scannerViewModel: ScannerViewModel,
    paddingValues: PaddingValues,
                        bDModel:RegistrarTareasViewModel,
                        navController: NavController,
                        viewModel: TareasViewModel) {

    Column(
        modifier = Modifier
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
            icono = Icons.Default.Create,
            multiLinea = false
        )//  cambiar el icono aquí

        SpaceAlto()
        val actividalesList by bDModel.notasList.collectAsState()
        var id: Long = 0
        LazyColumn {
            items(actividalesList) { item ->

                //eliminar elemento
                val eliminar = SwipeAction(
                    icon = rememberVectorPainter(Icons.Default.Delete),
                    background = Color.Red,
                    onSwipe = { bDModel.deleteNota(item) }
                )
                //endActions para eliminar de izquierda a derecha starActions de derecha a
                SwipeableActionsBox(endActions = listOf(eliminar), swipeThreshold = 100.dp) {

                    //val foto= byteArrayToBitmap(item.foto)
                    val foto= viewModel.byteArrayToBitmap(item.foto)
                    val imagen=viewModel.bitmapToImageBitmap(foto)

                    CardMain(
                        id = item.id.toString(),
                        viewModel,
                        nombre = item.nombre,
                        fecha = item.fecha,
                        descripcion = item.descripcion,
                        tipo = item.tipo,
                        imagen =  imagen,
                        imagenUri = item.fotoUri,
                        audio = item.audio,
                        onclickMostrarMas = { bDModel.cambiarMostrar() },
                        mostrarMAs = bDModel.mostrarMas
                    ) {
                        //navController.navigate("Editar/${item.id}")
                        viewModel.editar(true)
                        viewModel.idEstado(item.id)
                    }

                }

            }
        }

        if (viewModel.estado.editar) {
            ModalModificar(scannerViewModel,bDModel, viewModel, navController, id = viewModel.estado.id) { viewModel.editar(false)}

        }



    }
}








