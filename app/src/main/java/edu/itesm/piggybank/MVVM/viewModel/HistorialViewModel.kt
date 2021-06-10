package edu.itesm.piggybank.MVVM.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.itesm.piggybank.MVVM.model.HistorialModel
import edu.itesm.piggybank.MVVM.model.HistorialProvider


class HistorialViewModel: ViewModel() {
    val historialModelo = MutableLiveData<HistorialModel>()
    fun mensajeRandom(){
        val mensaje = HistorialProvider.getHistory()
        historialModelo.postValue(mensaje)
    }
}