package com.example.notastareas.room

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notastareas.models.Notas


@Database(entities = [Notas::class], version = 1 , exportSchema = false)
abstract class NotasDataBase :RoomDatabase() {

    abstract fun notasDao(): NotasDataBaseDao

}