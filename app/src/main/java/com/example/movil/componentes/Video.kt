package com.example.movil.componentes

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.LocusId
import android.media.browse.MediaBrowser
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.FileProvider
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.vistas.createImageFile
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Objects



@Composable
fun VideoGrabar(viewModel: FotosViewModel) {

    var videoUris by remember { mutableStateOf(listOf<Uri>()) }

    var videoUri by remember {
        mutableStateOf<Uri?>(null)
    }


    val context = LocalContext.current



    val videoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.CaptureVideo(),
        onResult = { success ->
            if (success && videoUri != null) {
                videoUris = videoUris.plus(videoUri!!)
                viewModel.videoUris=viewModel.videoUris.plus(videoUri!!)
            }
        }
    )

        IconButton(onClick = {
            val videoIntent = Intent(MediaStore.ACTION_VIDEO_CAPTURE)
            val file = context.createVideoFile()
            val uri = FileProvider.getUriForFile(
                Objects.requireNonNull(context),
                context.packageName + ".provider", file
            )
           videoUri=uri

            videoLauncher.launch(uri)
        }) {
            Icon(Icons.Filled.CheckCircle, contentDescription = "Grabar vídeo")
        }
       Text(text = viewModel.videoUri.toString())

        VideoPlayer(
            videoUri = videoUri,
            modifier = Modifier
                .height(400.dp)
                .fillMaxWidth()
        )


}

@SuppressLint("SimpleDateFormat")
fun Context.createVideoFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "MP4_" + timeStamp + "_"
    return File.createTempFile(
        imageFileName,
        ".mp4",
        externalCacheDir
    )
}


@Composable
fun VideoPlayer(videoUri: Uri?, modifier: Modifier = Modifier) {

    val context = LocalContext.current

    if(videoUri != null) {
        val exoPlayer = remember {
            SimpleExoPlayer.Builder(context).build().apply {
                setMediaItem(MediaItem.fromUri(videoUri))
                prepare()

            }
        }

        val playbackState = exoPlayer
        val isPlaying = playbackState?.isPlaying ?: false

        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = exoPlayer
                }
            },
            modifier = modifier
        )
    }
}

fun getVideoUri(context: Context): Uri {
    // 1
    val directory = File(context.cacheDir, "images")
    directory.mkdirs()
    // 2
    val file = File.createTempFile(
        "selected_image_",
        ".mp4",
        directory
    )
    // 3
    val authority = "com.daviddj.proyecto_final-djl.fileprovider"
    // 4
    return FileProvider.getUriForFile(
        context,
        authority,
        file,
    )
}
