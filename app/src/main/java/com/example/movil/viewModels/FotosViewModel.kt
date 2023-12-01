package com.example.movil.viewModels

import android.media.MediaRecorder
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import java.io.IOException

class FotosViewModel() :ViewModel(){

    var imagesUri by   mutableStateOf<List<Uri>>(emptyList())
    var imagenUri by mutableStateOf<Uri>(Uri.EMPTY)

    //agregamos solamente una imagen a la lista
    fun agregar (it: Uri){
        val nueva= imagesUri.toMutableList()
        nueva.add(it)
        imagesUri=nueva

    }

    //agreagamos una lista de imagenes
    fun agregarLista (it:List<Uri>){
        val nueva= imagesUri.toMutableList()
        it.forEach {it
            nueva.add(it)
        }
        imagesUri=nueva

    }



    //audio

}