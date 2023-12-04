package com.example.movil.state

import android.graphics.Bitmap

data class NotasEstado(val nombre: String="",
                       val fecha: String="",
                       val descripcion: String="",
                       val tipo: String="",
                       val mostrarAlerta: Boolean=false,
                       val tarea:Boolean=false,
                       val notas:Boolean=false,
                       val fotos:Boolean=false,
                       val foto: ByteArray?=null,
                       val audio:ByteArray?=null,
                       val fotoUri:String="",
                       val videoUri:String="",
                       val audios:Boolean=false,
                       val editar:Boolean=false,

                       val id:Long=0)
