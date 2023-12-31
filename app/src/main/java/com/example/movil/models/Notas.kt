package com.example.movil.models

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Crear nuestra entidades =tabla , atributos = acmpo
@Entity(tableName = "notas")
data class Notas(
    @PrimaryKey(autoGenerate = true)
    val id: Long= 0,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name="fecha")
    val fecha: String,
    @ColumnInfo(name="descripcion")
    val descripcion: String,
    @ColumnInfo(name="tipo")
    val tipo: String,
    @ColumnInfo(name="foto")
      val foto:  ByteArray?,
    @ColumnInfo(name="audio")
    val audio: ByteArray?,
    @ColumnInfo(name="fotoUri")
    val fotoUri: String,
    @ColumnInfo(name="videoUri")
    val videoUri: String

    )
