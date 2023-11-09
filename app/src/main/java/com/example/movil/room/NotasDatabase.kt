package com.example.movil

import androidx.room.Database

import androidx.room.RoomDatabase



@Database(entities = [Notas::class], version = 1 , exportSchema = false)
abstract class NotasDataBase :RoomDatabase() {

    abstract fun notasDao(): NotasDataBaseDao

}