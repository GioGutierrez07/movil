package com.example.movil.componentes


import android.media.MediaPlayer
import android.media.MediaRecorder

import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.movil.R
import com.example.movil.viewModels.FotosViewModel
import com.example.movil.viewModels.TareasViewModel
import java.io.File

@Composable
fun RecordButton(viewModel: TareasViewModel) {

   if(viewModel.audio!=null)return

    val context = LocalContext.current
    val mediaRecorder = remember { MediaRecorder() }
    val audioFile = File(context.cacheDir, "audio.3gp")
    var isRecording by remember { mutableStateOf(false) }


    var color by remember { mutableStateOf(Color.Black) }

    Icon(
        painter = painterResource(R.drawable.audio),
        contentDescription = "Grabar",
        modifier = Modifier
            .size(48.dp)
            .clickable {
                isRecording = !isRecording
                if (isRecording) {
                    startRecording(mediaRecorder, audioFile)
                    color = Color.Red
                } else {
                    viewModel.audio = stopRecording(mediaRecorder, audioFile)
                    color = Color.Black
                }
            },
                tint = color,
    )
}

@Composable
fun PlayButton(audioBytes: ByteArray?,viewModele: TareasViewModel) {

    if(audioBytes==null)return

    val context = LocalContext.current
    val mediaPlayer = remember { MediaPlayer() }
    val audioFile = File(context.cacheDir, "audio.3gp")
    var color by remember { mutableStateOf(Color.Black) }
    var isPlaying by remember {
        mutableStateOf(false)
    }

    Icon(
        imageVector = Icons.Default.PlayArrow,
        contentDescription = "Reproducir",
        modifier = Modifier
            .size(48.dp)
            .clickable {
                    isPlaying = !isPlaying
                if (isPlaying) {
                    startPlaying(mediaPlayer, audioBytes, audioFile)
                    color = Color.DarkGray
                } else {
                    stopPlaying(mediaPlayer)
                    color = Color.Red

                }
            },
        tint = color
    )
   // StopButton(mediaPlayer = mediaPlayer, isPlaying,viewModele)
}


@Composable
fun StopButton(mediaPlayer: MediaPlayer, isPlaying: Boolean,viewModele: TareasViewModel) {
    var color by remember { mutableStateOf(Color.Black) }


    Icon(
        painter = painterResource(R.drawable.detener),
        contentDescription = "Detener",
        modifier = Modifier
            .size(48.dp)
            .clickable {
                if (mediaPlayer.isPlaying) {
                    stopPlaying(mediaPlayer)
                    color = Color.Gray
                    !isPlaying

                   // viewModele.audio=null;
                   //
                }
            },
        tint = color
    )
}


fun startPlaying(mediaPlayer: MediaPlayer, audioBytes: ByteArray, audioFile: File) {
    audioFile.writeBytes(audioBytes)
    mediaPlayer.apply {
        setDataSource(audioFile.absolutePath)
        prepare()
        start()
    }
}

fun stopPlaying(mediaPlayer: MediaPlayer) {
    mediaPlayer.stop()
    mediaPlayer.release()
}


fun startRecording(mediaRecorder: MediaRecorder, audioFile: File) {
    mediaRecorder.apply {
        setAudioSource(MediaRecorder.AudioSource.MIC)
        setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
        setOutputFile(audioFile.absolutePath)
        setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
        prepare()
        start()
    }
}

fun stopRecording(mediaRecorder: MediaRecorder, audioFile: File): ByteArray {
    mediaRecorder.stop()
    mediaRecorder.release()
    return audioFile.readBytes()
}



