package com.example.movil.vistas


import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.modifier.modifierLocalConsumer

import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
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





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormularioEditarView(
    scannerViewModel: ScannerViewModel,
    fotosViewModel: FotosViewModel,
    bdTarea: RegistrarTareasViewModel,
    viewModel: TareasViewModel,
    navController: NavController,
    id: Long) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(
                    text = "Editar",
                    color= MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(R.font.press_start_2p)) ) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    MainIconButton(icon = Icons.Default.ArrowBack) {
                        bdTarea.recopialrDatpos=true
                        fotosViewModel.editarMultomedia=true
                        navController.popBackStack()
                    }
                }
            )
        }
    ) {
        ContentFormularioEditarView(scannerViewModel,fotosViewModel ,it,bdTarea,viewModel,navController,id)
    }
}


@Composable
fun ContentFormularioEditarView(
    scannerViewModel:ScannerViewModel,
    fotosViewModel: FotosViewModel,
    paddingValues: PaddingValues,
    bdTarea: RegistrarTareasViewModel,
    viewModel: TareasViewModel,
    navController: NavController,
    id: Long) {


    if(bdTarea.recopialrDatpos) {
        // recopilar informacion de la base de datos
        LaunchedEffect(Unit) {
            viewModel.getTaresById(id)
            bdTarea.recopialrDatpos=false
            fotosViewModel.imagesUri =
                viewModel.retornaListaUri(viewModel.estado.fotoUri)
            fotosViewModel.videoUris =
                viewModel.retornaListaUri(viewModel.estado.videoUri)

        }
    }


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        //verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {




        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(16.dp)
                .clip(CircleShape)

        ) {
            items(1) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconoSeleccion(
                        seleccionado = viewModel.estado.tarea,
                        iconoResId = R.drawable.tarea, // Cambia el icono de tarea aquí
                        texto = bdTarea.recopialrDatpos.toString(),
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
                    Modifier
                        .height(60.dp)
                        .fillMaxWidth(),
                    value = viewModel.estado.nombre,
                    onValueChange = { viewModel.onValue(it, "nombre") },
                    label = stringResource(id = R.string.NombreA)
                )

                SpaceAlto()

                MainTextFieldPersonalizado(
                    Modifier
                        .height(300.dp)
                        .fillMaxWidth(),
                    value = viewModel.estado.descripcion,
                    onValueChange = { viewModel.onValue(it, "descripcion") },
                    label = stringResource(id = R.string.labelDescripcion)
                )

                SpaceAlto(30.dp)

                SelectorFecha(viewModel)

                SpaceAlto()

                SelectorMultimedia(
                    editar = true,
                    fotosViewModel =fotosViewModel ,
                    navController = navController,
                    viewModel = viewModel,
                    camara = scannerViewModel
                )

                SpaceAlto(15.dp)
                Row {
                    MainButtonRegistrar(text = "Actualizar",) {
                        //lo que realizara el boton

                        if (!viewModel.validarCampos()) return@MainButtonRegistrar
                        //viewModel.validarCampos()

                        //actualizamos los datos
                        bdTarea.updateNota(
                            Notas(
                                id = id,
                                nombre = viewModel.estado.nombre,
                                fecha = viewModel.estado.fecha,
                                descripcion = viewModel.estado.descripcion,
                                tipo = if (viewModel.estado.tarea) "Tarea" else "Nota",
                                foto = viewModel.estado.foto,
                                audio = viewModel.audio,
                                fotoUri = viewModel.listaUri(fotosViewModel.imagesUri),
                                videoUri = viewModel.listaUri(fotosViewModel.videoUris)
                            )
                        )
                        viewModel.limpiar()
                        bdTarea.recopialrDatpos = true
                        fotosViewModel.editarMultomedia = true
                        navController.navigate("home")
                        //fotosViewModel.LimpiarListaEditar()

                    }

                    MainButtonRegistrar(
                        text = "Limpiar Formurio",
                        color = MaterialTheme.colorScheme.secondary
                    ) {
                        bdTarea.recopialrDatpos = true
                        fotosViewModel.LimpiarListaEditar()
                        viewModel.limpiar()
                    }
                }

                if (viewModel.estado.mostrarAlerta) {
                    Alert(title = "Alerta",
                        message = "Todos los campos son obligatorios",
                        confirmText = "Aceptar",
                        onConfirmClick = { viewModel.cancelAlert() }) { }
                }
            }
        }


    }
}

@Composable
fun ModalModificar(
        fotosViewModel: FotosViewModel,
        scannerViewModel: ScannerViewModel,
         bdTarea: RegistrarTareasViewModel,
                    viewModel: TareasViewModel,
                    navController: NavController,
                    id: Long,
                    onDismissRequest: () -> Unit) {




        Dialog(
            onDismissRequest = { onDismissRequest() },

            ) {

            // Contenido de la ventana modal
            LazyColumn(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.tertiary)
                    .padding(16.dp)
                    .clip(RoundedCornerShape(16.dp))

            ) {
                items(1) {


                    MainIconButton(icon = Icons.Default.ArrowBack) {

                        //bdTarea.recopialrDatpos=true

                        //fotosViewModel.LimpiarListaEditar()
                        navController.navigate("home")
                        onDismissRequest()

                    }

                        if(bdTarea.recopialrDatpos) {
                        // Contenido de la ventana modal, por ejemplo, algunos textos y un botón de cierre
                        LaunchedEffect(Unit) {
                            viewModel.getTaresById(id)
                            bdTarea.recopialrDatpos=false
                            fotosViewModel.imagesUri =
                                viewModel.retornaListaUri(viewModel.estado.fotoUri)
                            fotosViewModel.videoUris =
                                viewModel.retornaListaUri(viewModel.estado.videoUri)
                            fotosViewModel.videoUrisEditar =
                                viewModel.retornaListaUri(viewModel.estado.videoUri)
                            fotosViewModel.imagesUriEditar =
                                viewModel.retornaListaUri(viewModel.estado.fotoUri)

                        }
                    }






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
                            texto = bdTarea.recopialrDatpos.toString(),
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

                    SelectorMultimedia(true,fotosViewModel,navController, viewModel, scannerViewModel)
                    SpaceAlto()

                    MainButtonRegistrar(text = "Actualizar",) {
                        //lo que realizara el boton

                        if (!viewModel.validarCampos()) return@MainButtonRegistrar
                        //viewModel.validarCampos()

                        //actualizamos los datos
                        bdTarea.updateNota(
                            Notas(
                                id = id,
                                nombre = viewModel.estado.nombre,
                                fecha = viewModel.estado.fecha,
                                descripcion = viewModel.estado.descripcion,
                                tipo = if (viewModel.estado.tarea) "Tarea" else "Nota",
                                foto = viewModel.estado.foto,
                                audio = viewModel.audio,
                                fotoUri = viewModel.listaUri(fotosViewModel.imagesUriEditar),
                                videoUri = viewModel.listaUri(fotosViewModel.videoUrisEditar)
                            )
                        )

                        navController.navigate("home")
                        fotosViewModel.LimpiarListaEditar()
                        bdTarea.recopialrDatpos=true
                        onDismissRequest()
                    }

                    SpaceAlto()
                    MainButtonRegistrar(
                        text = "Limpiar Formurio",
                        color = MaterialTheme.colorScheme.secondary
                    ) {
                        fotosViewModel.LimpiarListaEditar()
                        viewModel.limpiar()
                    }

                    if (viewModel.estado.mostrarAlerta) {
                        Alert(title = "Alerta",
                            message = "Todos los campos son obligatorios",
                            confirmText = "Aceptar",
                            onConfirmClick = { viewModel.cancelAlert() }) { }
                    }
                }
            }
        }
    }










