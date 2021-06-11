package edu.itesm.piggybank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class IncrementosAdapter(private val incrementos: List<IncrementoBase>): RecyclerView.Adapter<IncrementosAdapter.IncrementosViewHolder>() {

    inner class IncrementosViewHolder(v: View): RecyclerView.ViewHolder(v){
        var tipoAdapter = v.findViewById<TextView>(R.id.tipo_gasto)
        var gasto = v.findViewById<TextView>(R.id.cantidad_gasto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncrementosViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val renglon_layout = inflater.inflate(R.layout.renglon_layout_incremento,parent,false)
        return IncrementosViewHolder(renglon_layout)
    }

    override fun onBindViewHolder(holder: IncrementosViewHolder, position: Int) {
        val dato = incrementos[position]
        val tipoAda = dato.tipo
        val gastoAda = dato.cantidad
        holder.tipoAdapter.text = tipoAda
        holder.gasto.text = gastoAda.toString()
    }

    override fun getItemCount(): Int {
        return incrementos.size
    }


}