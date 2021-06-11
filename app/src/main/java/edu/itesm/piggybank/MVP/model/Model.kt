package edu.itesm.piggybank.MVP.model

import android.os.Handler
import edu.itesm.piggybank.MVP.contract.Contract
import java.util.*

class Model : Contract.Model {

    private val listaMensajes = Arrays.asList(
        "Juguetes",
        "iPads",
        "iPhones",
        "Pelotas",
        "Muñecas",
        "Legos",
        "Nerf",
        "Guitarra",
        "Rompecabezas",
        "Videojuegos",
        "Funko",
        "Audifonos",
        "Juegos de mesa",
        "Patineta",
        "Patines",
    )

    fun randomMensaje() : String{
        val posicion = (0..listaMensajes.size-1).random()
        return listaMensajes[posicion]
    }

    override fun getSiguienteMensaje(onFinishListener: Contract.Model.OnFinish) {
        Handler().postDelayed({onFinishListener.onFinished(randomMensaje())}, 750)
    }


}