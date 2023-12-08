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

    private val _notasList2= MutableStateFlow<List<Notas>>(emptyList())
    val notasList2 = _notasList2.asStateFlow()

    var nota by mutableStateOf("Nota")

    var recopialrDatpos by mutableStateOf(true)

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

    fun buscarCoin(nota:String) {
            viewModelScope.launch(Dispatchers.IO) {
                repositorio.getNotasByTipo(nota).collect { item ->
                    if (item.isEmpty()) {
                        _notasList2.value = emptyList()
                    } else {
                        _notasList2.value = item
                    }

                }
            }

    }



    fun addNota(nota: Notas)= viewModelScope.launch { repositorio.addNota(nota) }
    fun updateNota(nota: Notas)= viewModelScope.launch { repositorio.updateNota(nota) }
    fun deleteNota(nota: Notas) = viewModelScope.launch { repositorio.deleteNota(nota) }
}