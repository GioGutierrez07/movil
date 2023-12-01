package com.example.movil.navegacion



import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movil.notificaciones.Notification
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.RegistrarTareasViewModel
import com.example.movil.viewModels.ScannerViewModel


import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.FormularioEditarView

import com.example.movil.vistas.FormularioView
import com.example.movil.vistas.HomeView
import com.example.movil.vistas.ModalModificar
import com.example.movil.vistas.NotasApp
import com.example.movil.vistas.TabsView


@Composable
fun NavManager(
    fotosViewModel: FotosViewModel,
    camare:ScannerViewModel,
    bDModel: RegistrarTareasViewModel,
    viewModel: TareasViewModel,
    windowSize:WindowWidthSizeClass
){

    val navController = rememberNavController()

    //startDestination es donede inicia nuestra app
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            NotasApp(fotosViewModel,camare,bDModel, navController, windowSize,viewModel)
        }

        composable("Formulario"){
            FormularioView(fotosViewModel,camare,bDModel ,viewModel , navController)
        }

        composable("Tabs"){
            TabsView(fotosViewModel,camare,viewModel,navController)
        }

        composable("Editar/{id}", arguments = listOf(
            navArgument("id"){
                type= NavType.LongType }
        )){
            val id =it.arguments?.getLong("id") ?:0
            FormularioEditarView(bDModel ,viewModel , navController,id)
        }


    }
}
