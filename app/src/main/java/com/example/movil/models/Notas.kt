package com.example.movil.models

data class Notas(
    val nombre: String="",
    val fecha: String="",
    val descripcion: String="",
    val mostrarAlerta: Boolean=false,
    val tarea:Boolean=false,
    val notas:Boolean=false,
    val fotos:Boolean=false,
    val audios:Boolean=false
)
