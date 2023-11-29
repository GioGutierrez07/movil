package com.example.movil.componentes

import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import java.io.IOException

@Composable
fun VoiceNotesComposable() {
    var isRecording by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val mediaRecorder = remember { MediaRecorder() }
    val mediaPlayer = remember { MediaPlayer() }
    val audioFilePath = context.filesDir.absolutePath + "/voice_note.3gp"

    Button(onClick = {
        if (isRecording) {
            stopRecording(mediaRecorder)
            isRecording = false
        } else {
            startRecording(mediaRecorder, audioFilePath)
            isRecording = true
        }
    }) {
        Text(if (isRecording) "Detener Grabación" else "Grabar Nota de Voz")
    }

    Button(onClick = {
        if (isPlaying) {
            mediaPlayer.stop()
            isPlaying = false
        } else {
            startPlaying(mediaPlayer, audioFilePath)
            isPlaying = true
        }
    }) {
        Text(if (isPlaying) "Detener Reproducción" else "Reproducir Nota de Voz")
    }
}

fun startRecording(mediaRecorder: MediaRecorder, filePath: String) {
    try {
        mediaRecorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
            setOutputFile(filePath)
            prepare()
            start()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun stopRecording(mediaRecorder: MediaRecorder) {
    mediaRecorder.apply {
        stop()
        release()
    }
}

fun startPlaying(mediaPlayer: MediaPlayer, filePath: String) {
    try {
        mediaPlayer.apply {
            setDataSource(filePath)
            prepare()
            start()
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}