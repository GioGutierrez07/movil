package com.example.movil.vistas

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable


import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.movil.R

import com.example.movil.componentes.Alert


import com.example.movil.componentes.IconoSeleccion
import com.example.movil.componentes.MainButtonRegistrar
import com.example.movil.componentes.MainIconButton
import com.example.movil.componentes.MainTextFieldPersonalizado


import com.example.movil.componentes.SelectorFecha
import com.example.movil.componentes.SelectorMultimedia
import com.example.movil.componentes.SpaceAlto
import com.example.movil.componentes.TitleBar

import com.example.movil.models.Notas
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.RegistrarTareasViewModel
import com.example.movil.viewModels.ScannerViewModel

import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.utils.ReplyNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioView(
    fotosViewModel: FotosViewModel,
    camara:ScannerViewModel,
    bdTarea: RegistrarTareasViewModel,
    viewModel: TareasViewModel,
    navController: NavController, ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { TitleBar(name = "Registrar notas o tareas") },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        navController.navigate("home")
                    }
                }
            )
        }


    ) {
        ContentFormularioView(fotosViewModel,camara,it,bdTarea,viewModel,navController)
    }
}


@Composable
fun ContentFormularioView(fotosViewModel: FotosViewModel,
                          camara: ScannerViewModel,
                          paddingValues: PaddingValues,
                          bdTarea: RegistrarTareasViewModel,
                          viewModel: TareasViewModel,
                          navController: NavController) {
    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1){


        //multimedia
       // MultimediaPickerExample()
        //audio
       // VoiceNotesComposable()
            // AudioRecorderButton()

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconoSeleccion(
                seleccionado = viewModel.estado.tarea,
                iconoResId = R.drawable.tarea, // Cambia el icono de tarea aquí
                texto = "Tarea",
                onClick = {
                    viewModel.esTarea()

                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            IconoSeleccion(
                seleccionado = viewModel.estado.notas,
                iconoResId = R.drawable.nota, // Cambia el icono de notas aquí
                texto = "Notas",
                onClick = {
                    viewModel.esNota()
                }
            )
        }

        MainTextFieldPersonalizado(
            value = viewModel.estado.nombre,
            onValueChange = { viewModel.onValue(it, "nombre") },
            label = stringResource(id = R.string.NombreA)
        )

        SpaceAlto()

        MainTextFieldPersonalizado(
            modifer = Modifier.fillMaxSize().height(300.dp).padding(horizontal = 30.dp),
            value = viewModel.estado.descripcion,
            onValueChange = { viewModel.onValue(it, "descripcion") },
            label = stringResource(id = R.string.labelDescripcion)
        )
        SpaceAlto()
        SelectorFecha(viewModel)
        SpaceAlto()

        SelectorMultimedia(navController,viewModel,camara)

        SpaceAlto()

            Row {

                MainButtonRegistrar(text = "Registrar" ) {
                    if(!viewModel.validarCampos()) return@MainButtonRegistrar

                    bdTarea.addNota(
                        Notas(
                            nombre = viewModel.estado.nombre,
                            fecha = viewModel.estado.fecha,
                            descripcion = viewModel.estado.descripcion,
                            tipo= if(viewModel.estado.tarea) "Tarea" else "Nota",
                            foto = viewModel.estado.foto,
                            audio=viewModel.audio,
                            //fotoUri = fotosViewModel.imagenUri.toString()
                            fotoUri = viewModel.listaUri(fotosViewModel.imagesUri),
                            videoUri = viewModel.listaUri(fotosViewModel.videoUris)
                        )
                    )
                    viewModel.limpiar()
                    //regresamos a la pantalla principal
                    navController.navigate("Home")
                }

                MainButtonRegistrar(text = "Limpiar Formurio", color=MaterialTheme.colorScheme.tertiary) {
                    //lo que realzara el boton
                    viewModel.limpiar()
                }

            }

         //  Text(text = fotosViewModel.videoUris.toString())

        if(viewModel.estado.mostrarAlerta){
            Alert(title = "Alerta",
                message = "Todos los campos son obligatorios",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel.cancelAlert() }) { }
        }
    }
    }
}




