package com.example.movil.di

import android.content.Context
import androidx.room.Room
import com.example.movil.room.NotasDataBase
import com.example.movil.room.NotasDataBaseDao


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//inyeccion de dependencias(metodos ,Clases)
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    //se define lo que se va estar inyectando en otras classes
    @Singleton
    @Provides
    fun providesNotasDao(notasDataBase: NotasDataBase): NotasDataBaseDao {
        return notasDataBase.notasDao()
    }

    //instamciia de la creacion de la base de datos
    @Singleton
    @Provides
    fun proviesNotasDataBase(@ApplicationContext context: Context): NotasDataBase{

        return Room.databaseBuilder(//creador de  la base de datos
            context,
            NotasDataBase::class.java,
            "notas32_db"
        ).fallbackToDestructiveMigration().build()
    }



}