package com.example.movil.vistas

import android.annotation.SuppressLint
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movil.R
import com.example.movil.componentes.BotonFlotante
import com.example.movil.componentes.VideoGrabar
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.ScannerViewModel
import com.example.movil.viewModels.TareasViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TabsGaleriaView(
    fotosViewModel: FotosViewModel,
    viewModel: ScannerViewModel
    ,tareaviewModel: TareasViewModel,
    navController: NavController
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(
                    text = "Fotos Guardadas",
                    color= MaterialTheme.colorScheme.secondary,
                    fontFamily = FontFamily(Font(R.font.press_start_2p)) ) },
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
                navController.navigate("home")

            }
        }
    ) {

        var selectedTab by remember { mutableStateOf(0) }
        val tabs = listOf("Coleccion", "Video")



        Column (Modifier.padding(50.dp)){

            TabRow(selectedTabIndex = selectedTab,
                contentColor = MaterialTheme.colorScheme.tertiary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab])
                    )
                }) {

                tabs.forEachIndexed { index, titulo ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(text = titulo, fontSize = 7.sp,
                            fontFamily = FontFamily(Font(R.font.press_start_2p))
                            ,
                            ) },
                    )
                }

            }

            when (selectedTab) {
                0 -> CollectionGalleryView(fotosViewModel).apply {  }
                1->  VideoGrabar(viewModel = fotosViewModel).apply {  }
            }
        }
    }
}

