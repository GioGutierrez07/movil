package com.example.movil.vistas

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.movil.componentes.BotonFlotante
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.ScannerViewModel
import com.example.movil.viewModels.TareasViewModel
import com.example.movil.vistas.utils.ReplyNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabsView(
            fotosViewModel: FotosViewModel,
            viewModel: ScannerViewModel
            ,tareaviewModel:TareasViewModel,
             navController: NavController
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Galeria",color= MaterialTheme.colorScheme.onPrimary) },
                colors= TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        floatingActionButton = {
            BotonFlotante(
                icono = Icons.Filled.ArrowBack,
                contenedorColor = Color.Gray,
                colorContenido = Color.White,
            ){

                    navController.navigate("Formulario")
            }
        }
    ) {

        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Galeria", "Camara", "Coleccion")

        Column (Modifier.padding(50.dp)){

            TabRow(selectedTabIndex = selectedTab,
                contentColor = Color.Black,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                    )
                }) {

                tabs.forEachIndexed { index, titulo ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text = titulo) },
                    )
                }

            }
            when (selectedTab) {
                0 -> GalleryView(viewModel,fotosViewModel).apply { viewModel.cleanText() }
                1 -> CameraView(viewModel, fotosViewModel).apply { viewModel.cleanText() }
                2 -> CollectionGalleryView(fotosViewModel)
            }
        }
    }
}


