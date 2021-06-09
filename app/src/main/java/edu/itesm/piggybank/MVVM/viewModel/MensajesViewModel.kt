package edu.itesm.piggybank.MVVM.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import edu.itesm.piggybank.MVVM.model.MensajesModel
import edu.itesm.piggybank.MVVM.model.MensajesProvider

class MensajesViewModel: ViewModel() {
    val mensajesModelo = MutableLiveData<MensajesModel>()
    fun mensajeRandom(){
        val mensaje = MensajesProvider.randomMensaje()
        mensajesModelo.postValue(mensaje)
    }
}