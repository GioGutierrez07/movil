package com.example.movil.viewModels

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movil.repositorio.NotasRepositorio
import com.example.movil.state.NotasEstado

import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.nio.ByteBuffer
import javax.inject.Inject

@HiltViewModel
class TareasViewModel @Inject constructor(private val repositorio: NotasRepositorio) : ViewModel() {

    var estado by mutableStateOf(NotasEstado())
        private set

    var audio by mutableStateOf<ByteArray?>(null)


  ///estados de la para tomar una foto
  var capturedImage by mutableStateOf<ImageBitmap?>(null)
   var imagenBitmap by mutableStateOf<Bitmap?>(null)
    val context: Context
        @Composable
        get() = LocalContext.current

    val launcher: ManagedActivityResultLauncher<Void?, Bitmap?>
        @Composable
        get() = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.TakePicturePreview()
        ) { bitmap ->
            // Manejo del bitmap de la imagen capturada
            // Por ejemplo, mostrarlo en la UI o guardarlo en el almacenamiento

            // Manejo del bitmap de la imagen capturada
            if (bitmap != null) {
                capturedImage = bitmap.asImageBitmap()
                imagenBitmap=bitmap
            }
        }


    fun onValue(valor: String , texto:String){

        when(texto){
            "nombre"-> estado = estado.copy(nombre=valor)
            "fecha"-> estado = estado.copy(fecha = valor)
            "descripcion"-> estado = estado.copy(descripcion=valor)
            "tipo"-> estado=estado.copy(tipo = valor)

        }
    }

    fun foto(it: ByteArray){
        estado=estado.copy(foto=it)
    }


    fun getTaresById(id:Long){
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.getNotasByID(id).collect { item ->
                estado = estado.copy(
                    nombre = item.nombre,
                    fecha = item.fecha,
                    descripcion = item.descripcion,
                    tipo = item.tipo,
                    foto=item.foto

                )
            }
        }
    }




   fun idEstado(it:Long){
       estado=estado.copy(
           id=it
       )
   }

    fun limpiar() {
        estado = estado.copy(
            nombre = "",
            descripcion = "",
            fecha = "",
            fotos = false,
            audios = false,
            tarea = false,
            notas = false,

        )
        audio=null
        imagenBitmap=null
        capturedImage=null
    }

    fun editar(editar: Boolean){
        estado=estado.copy( editar = editar)
    }

    fun esTarea(){
        estado=estado.copy( tarea = true,
            notas = false)
    }

    fun esNota(){
        estado=estado.copy( notas = true,
            tarea=false)
    }
    fun esAudio(){
        estado=estado.copy( audios = true,
            fotos =false)
    }
    fun esFoto(){
        estado=estado.copy( audios = false,
            fotos =true)
    }
    fun buscar(){
    }

    fun validarCampos(): Boolean{
        val tarea=estado.tarea
        val notas=estado.notas
        val nombre=estado.nombre
        val descripcion=estado.descripcion
        val fecha=estado.fecha
       // val fotos=estado.fotos
       // val audios=estado.audios

        if(!tarea  && !notas ){
            estado= estado.copy(mostrarAlerta = true)
            return false
        }
        if(nombre =="" || descripcion == "" || fecha==""){
            estado=estado.copy(mostrarAlerta = true)
            return false
        }
        return true

    }

    fun cancelAlert() {
        estado = estado.copy(
            mostrarAlerta = false
        )
    }

    fun bitmapToByteArray(bitmap: Bitmap?): ByteArray {

        val stream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.PNG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(byteArray: ByteArray?): Bitmap? {
        return if (byteArray != null) {
            BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        } else {
            null
        }
    }


    fun bitmapToImageBitmap(bitmap: Bitmap?): androidx.compose.ui.graphics.ImageBitmap? {
        return bitmap?.asImageBitmap()
    }


}