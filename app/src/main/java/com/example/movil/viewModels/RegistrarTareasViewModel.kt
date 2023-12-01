package com.example.movil.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movil.models.Notas
import com.example.movil.repositorio.NotasRepositorio

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class RegistrarTareasViewModel @Inject constructor(private val repositorio: NotasRepositorio) : ViewModel() {


    private val _notasList= MutableStateFlow<List<Notas>>(emptyList())
    val notasList = _notasList.asStateFlow()
    var mostrarMas by mutableStateOf(false)

    fun cambiarMostrar(){
        if(mostrarMas){
            mostrarMas=false
        }else{
            mostrarMas=true
        }
    }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repositorio.getAllNotas().collect{item->
                if(item.isEmpty()){
                    _notasList.value= emptyList()
                }else{
                    _notasList.value=item
                }

            }
        }

    }

    fun addNota(nota: Notas)= viewModelScope.launch { repositorio.addNota(nota) }
    fun updateNota(nota: Notas)= viewModelScope.launch { repositorio.updateNota(nota) }
    fun deleteNota(nota: Notas) = viewModelScope.launch { repositorio.deleteNota(nota) }
}