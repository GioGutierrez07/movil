package com.example.movil.navegacion



import androidx.compose.runtime.Composable

import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movil.viewModels.RegistrarTareasViewModel


import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.FormularioEditarView

import com.example.movil.vistas.FormularioView
import com.example.movil.vistas.HomeView


@Composable
fun NavManager(
    bDModel: RegistrarTareasViewModel,
    viewModel: TareasViewModel
){

    val navController = rememberNavController()

    //startDestination es donede inicia nuestra app
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(bDModel, navController)
        }

        composable("Formulario"){
            FormularioView(bDModel ,viewModel , navController)
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
