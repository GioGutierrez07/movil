package com.example.movil.room



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

import com.example.movil.models.Notas

import kotlinx.coroutines.flow.Flow


//interface-> Repositorio ->viewModel ->view
@Dao
interface NotasDataBaseDao {
    //CRUD
    //desde la esta interface la parasremos al repositorio
    @Query("SELECT * FROM notas")
    fun getNotas(): Flow<List<Notas>>

    @Query("SELECT * FROM notas WHERE id= :id")
    fun gerNotasByID(id: Long): Flow<Notas>

    @Query("SELECT * FROM notas WHERE tipo LIKE :tipo || '%' ")
    fun gerNotasByTipo(tipo: String):Flow<List<Notas>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: Notas)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(notes: Notas)

    @Delete
    suspend fun delete(notes: Notas)

}