package com.example.movil.repositorio


import com.example.movil.models.Notas
import com.example.movil.room.NotasDataBaseDao


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NotasRepositorio @Inject constructor(private  val notasDataBaseDao: NotasDataBaseDao){

    suspend fun addNota(nota : Notas)= notasDataBaseDao.insert(nota)
    suspend fun updateNota(nota :Notas)= notasDataBaseDao.update(nota)
    suspend fun deleteNota(nota :Notas)= notasDataBaseDao.delete(nota)
    fun getAllNotas(): Flow<List<Notas>> = notasDataBaseDao.getNotas().flowOn(Dispatchers.IO).conflate()
    fun getNotasByID(id:Long): Flow<Notas> = notasDataBaseDao.gerNotasByID(id).flowOn(Dispatchers.IO).conflate()

}