package com.example.movil.componentes

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.movil.R
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.ScannerViewModel
import com.example.movil.viewModels.TareasViewModel



@Composable
fun SelectorMultimedia(
    editar:Boolean,
    fotosViewModel: FotosViewModel,
    navController: NavController,
    viewModel: TareasViewModel,
    camara: ScannerViewModel
) {

    fotosViewModel.editarMultomedia=editar
    val context = viewModel.context
    // Estado para almacenar la imagen capturada
    val launcher = viewModel.launcher

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconoSeleccion(
            seleccionado = viewModel.estado.fotos,
            iconoResId = R.drawable.fotos, // Cambia el icono
            texto = "FOTOS",
            onClick = {
                if(fotosViewModel.editarMultomedia){
                    navController.navigate("TabsEditarView")
                }else{
                    navController.navigate("Tabs")
                }


            }
        )
        SpaceAncho()

        PlayButton(audioBytes = viewModel.audio,viewModel)

    SpaceAncho()

        RecordButton(viewModel)

  }
}

@Composable
fun Multimedia(
    urisimagen:String,
    urisVideo: String,
    fotosViewModel: FotosViewModel,
    navController: NavController,
    viewModel: TareasViewModel,
    camara: ScannerViewModel
) {

    val context = viewModel.context
    // Estado para almacenar la imagen capturada
    val launcher = viewModel.launcher

        IconoSeleccion(
            seleccionado = viewModel.estado.fotos,
            iconoResId = R.drawable.baseline_photo_library_24, // Cambia el icono
            texto = "Multimedia",
            onClick = {
                    fotosViewModel.imagesUri= viewModel.retornaListaUri(urisimagen)
                    fotosViewModel.videoUris= viewModel.retornaListaUri(urisVideo)
                navController.navigate("TabsGaleria")

            }
        )
}