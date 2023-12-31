package com.example.movil.vistas

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.textInputServiceFactory
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.movil.R
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.ScannerViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CollectionGalleryView( viewModel: FotosViewModel) {
    val context = LocalContext.current

    var imagesUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    val multiplePhoto = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickMultipleVisualMedia(maxItems = 10)
    ) {
        imagesUris = it
        //agregamos una colleccion de imagenes
        viewModel.agregarLista(it)
        //viewModel.videoUrisEditar?.plus(it)
    }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                multiplePhoto.launch(
                    PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                )
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_photo_library_24),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.tertiary
                )
            }

        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            LazyRow {
                items(1) {
                    if (viewModel.imagesUri != null) {
                        viewModel.imagesUri?.forEach { uri ->
                            AsyncImage(
                                model = ImageRequest.Builder(context).data(uri)
                                    .crossfade(enable = true).build(),
                                contentDescription = "",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 10.dp)
                                    .clickable { viewModel.eliminarDeLaLista(uri) }
                            )
                        }
                    }

                }
            }
        }
    }
}




