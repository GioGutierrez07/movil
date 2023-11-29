package com.example.movil.vistas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import com.example.movil.R

import com.example.movil.componentes.Alert
import com.example.movil.componentes.AudioRecorderButton
import com.example.movil.componentes.CameraButtonExample
import com.example.movil.componentes.IconoSeleccion
import com.example.movil.componentes.MainButtonRegistrar
import com.example.movil.componentes.MainIconButton
import com.example.movil.componentes.MainTextFieldPersonalizado
import com.example.movil.componentes.MultimediaPickerExample
import com.example.movil.componentes.SelectorFecha
import com.example.movil.componentes.SelectorMultimedia
import com.example.movil.componentes.SpaceAlto
import com.example.movil.componentes.TitleBar

import com.example.movil.models.Notas
import com.example.movil.viewModels.RegistrarTareasViewModel

import com.example.movil.viewModels.TareasViewModel




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioView(
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
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentFormularioView(it,bdTarea,viewModel,navController)
    }
}


@Composable
fun ContentFormularioView(paddingValues: PaddingValues,
                          bdTarea: RegistrarTareasViewModel,
                          viewModel: TareasViewModel,
                          navController: NavController) {
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //multimedia
        MultimediaPickerExample()
        //audio
        AudioRecorderButton()
        //tomar foto
        CameraButtonExample()
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
            value = viewModel.estado.descripcion,
            onValueChange = { viewModel.onValue(it, "descripcion") },
            label = stringResource(id = R.string.labelDescripcion)
        )
        SpaceAlto()
        SelectorFecha(viewModel)
        SpaceAlto()

        SelectorMultimedia(viewModel )
        SpaceAlto()



        MainButtonRegistrar(text = "registrar" ) {
            //lo que realzara el boton
            viewModel.validarCampos()
            //guardaer los datos de la tarea

            bdTarea.addNota(
                Notas(
                    nombre = viewModel.estado.nombre,
                    fecha = viewModel.estado.fecha,
                    descripcion = viewModel.estado.descripcion,
                    tipo= if(viewModel.estado.tarea) "Tarea" else "Nota"
                )
            )
            //regresamos a la pantalla principal
            navController.popBackStack()
        }

        SpaceAlto()
        MainButtonRegistrar(text = "Limpiar Formurio", color=MaterialTheme.colorScheme.tertiary) {
            //lo que realzara el boton
            viewModel.limpiar()
        }

        if(viewModel.estado.mostrarAlerta){
            Alert(title = "Alerta",
                message = "Todos los campos son obligatorios",
                confirmText = "Aceptar",
                onConfirmClick = { viewModel.cancelAlert() }) { }
        }
    }
}




