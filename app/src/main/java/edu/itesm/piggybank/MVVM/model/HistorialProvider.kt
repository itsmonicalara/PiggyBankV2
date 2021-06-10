package edu.itesm.piggybank.MVVM.model

class HistorialProvider {




    companion object{
        fun getHistory(): HistorialModel {
            val posicion = (0..incrementos.size-1).random()
            return incrementos[posicion]
        }

        private val incrementos = listOf<HistorialModel>(
            HistorialModel(razon = "Cumpleaños", cantidad = 500.0),
            HistorialModel(razon = "Domingo", cantidad = 300.0),
            HistorialModel(razon = "Domingo", cantidad = 300.0),
        )
    }
}