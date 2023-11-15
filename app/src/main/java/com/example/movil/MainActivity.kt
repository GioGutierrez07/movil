package com.example.movil



import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface

import androidx.compose.ui.Modifier

import com.example.movil.navegacion.NavManager
import com.example.movil.ui.theme.MovilTheme
import com.example.movil.viewModels.RegistrarTareasViewModel

import com.example.movil.viewModels.TareasViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint  //indica que toda la ativity utilizara dagger y hilt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tareaViewM: RegistrarTareasViewModel by viewModels()
        val estadoTarea: TareasViewModel by viewModels()
        setContent {

            MovilTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavManager(bDModel =tareaViewM , viewModel = estadoTarea)
                }
            }
        }
    }
}


