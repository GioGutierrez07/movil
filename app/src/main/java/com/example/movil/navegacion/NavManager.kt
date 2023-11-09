package com.example.movil.navegacion


import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movil.RegistrarTareaViewModel
import com.example.movil.viewModels.TareasViewModel

import com.example.notastareas.views.FormularioEditarView
import com.example.notastareas.views.FormularioView
import com.example.notastareas.views.HomeView


@Composable
fun NavManager(
    BDModel: RegistrarTareaViewModel,
    viewModel: TareasViewModel
){

    val navController = rememberNavController()

    //startDestination es donede inicia nuestra app
    NavHost(navController = navController, startDestination = "Home"){
        composable("Home"){
            HomeView(BDModel, navController)
        }

        composable("Formulario"){
            FormularioView(BDModel ,viewModel , navController)
        }

        composable("Editar/{id}", arguments = listOf(
            navArgument("id"){
                type= NavType.LongType }
        )){
            val id =it.arguments?.getLong("id") ?:0
            FormularioEditarView(BDModel ,viewModel , navController,id)
        }



    }
}
