package com.example.movil.models

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
)
