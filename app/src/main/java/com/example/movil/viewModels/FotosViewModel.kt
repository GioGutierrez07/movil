package com.example.movil.viewModels

import android.content.Context
import android.media.MediaRecorder
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.IOException

class FotosViewModel() :ViewModel(){

    var imagesUri by  mutableStateOf<List<Uri>?>(emptyList())
    var imagenUri by mutableStateOf<Uri?>(Uri.EMPTY)



   //agregamos solamente una imagen a la lista
    fun agregar (it: Uri){
        val nueva= imagesUri?.toMutableList()
        nueva?.add(it)
        imagesUri=nueva

    }

    //agreagamos una lista de imagenes
    fun agregarLista (it:List<Uri>){
        val nueva= imagesUri?.toMutableList()
        it.forEach {it
            nueva?.add(it)
        }
        imagesUri=nueva

    }

    //Variables para el video
    var videoUri  by  mutableStateOf<Uri?>(null)
   // var videoUris by  mutableStateOf<List<Uri>>(emptyList())
    var videoUris by mutableStateOf<List<Uri>?>(emptyList())

    //Agragar a lista de video
    fun agregarVideo (it: Uri){
        val nueva= videoUris?.toMutableList()
        nueva?.add(it)
        videoUris=nueva

    }

    //agreagamos una lista de videos
    fun agregarListaVideo (it:List<Uri>){
        val nueva= videoUris?.toMutableList()
        it.forEach {it
            nueva?.add(it)
        }
        videoUris=nueva

    }

    fun LimpiarListas(){
        val list = listOf<Uri>()
        imagesUri=list
        videoUris=list
    }

    fun eliminarVideo(video:Uri){
        val nueva= videoUris?.toMutableList()
        nueva?.remove(video)
        videoUris=nueva
    }
    fun eliminarDeLaLista(imagem:Uri){

        val nueva= imagesUri?.toMutableList()
        nueva?.remove(imagem)
        imagesUri=nueva
    }
    //audio

}