package edu.itesm.piggybank.MVVM.model

class MensajesProvider {

    companion object{
        fun randomMensaje():MensajesModel{
            val posicion = (0..mensajes.size-1).random()
            return mensajes[posicion]
        }

        private val mensajes = listOf<MensajesModel>(
            MensajesModel(mensaje = "hola"),
            MensajesModel(mensaje = "adios")
        )
    }
}