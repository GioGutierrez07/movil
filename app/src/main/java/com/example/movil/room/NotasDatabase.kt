package com.example.movil.room

import androidx.room.Database

import androidx.room.RoomDatabase

import com.example.movil.models.Notas

/// definimos las entidades [Notas::class,] puedes ser mass solo se separan por coma
@Database(entities = [Notas::class], version = 1 , exportSchema = false)
abstract class NotasDataBase :RoomDatabase() {

    abstract fun notasDao(): NotasDataBaseDao
    //apunta a nuestra clase dao
}