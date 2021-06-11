package edu.itesm.piggybank.MVVM.model

class HistorialProvider {




    companion object{
        fun getHistory(): HistorialModel {
            val posicion = (0..incrementos.size-1).random()
            return incrementos[posicion]
        }

        private val incrementos = listOf<HistorialModel>(
            HistorialModel(nombre = "Básico", precio = 50.0, cararectistica = "Limite de 20 productos", periodo = "Mensual"),
            HistorialModel(nombre = "Medio", precio = 100.0, cararectistica = "Limite de 50 productos", periodo = "Mensual"),
            HistorialModel(nombre = "Pro", precio = 150.0, cararectistica = "Limite de 100 productos",periodo = "Mensual"),
            HistorialModel(nombre = "Master", precio = 200.0, cararectistica = "Sin limite de productos",periodo = "Mensual"),
            HistorialModel(nombre = "De Vida", precio = 500.0, cararectistica = "Sin limite de productos",periodo = "Anual"),
        )
    }
}