package com.example.notastareas.di

import android.content.Context
import androidx.room.Room
import com.example.notastareas.room.NotasDataBase
import com.example.notastareas.room.NotasDataBaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {



    @Singleton
    @Provides
    fun providesNotasDao(notasDataBase: NotasDataBase): NotasDataBaseDao{
        return notasDataBase.notasDao()
    }

    @Singleton
    @Provides
    fun proviesNotasDataBase(@ApplicationContext context: Context): NotasDataBase{

        return Room.databaseBuilder(
            context,
            NotasDataBase::class.java,
            "notas_db"
        ).fallbackToDestructiveMigration().build()
    }



}