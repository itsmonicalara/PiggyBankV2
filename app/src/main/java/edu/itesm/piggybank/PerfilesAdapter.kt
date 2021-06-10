package edu.itesm.piggybank


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PerfilesAdapter(private val perfiles : List<Perfil>,  var tipoGasto: PerfilesFragment)
    : RecyclerView.Adapter<PerfilesAdapter.PerfilViewHolder>(){


    var cantidad = 0

    inner class PerfilViewHolder(renglon: View) : RecyclerView.ViewHolder(renglon){

        var nombre = renglon.findViewById<TextView>(R.id.nombre)
        var foto = renglon.findViewById<ImageView>(R.id.foto)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val renglon_vista = inflater.inflate(R.layout.perfiles_renglon,parent, false)
        return PerfilViewHolder(renglon_vista)
    }

    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {

        val perfil = perfiles[position]
        holder.foto.setImageResource(perfil.picture)
        holder.nombre.text = perfil.tipo
        holder.itemView.setOnClickListener(){
            tipoGasto.tipoGasto = perfil.tipo
            Log.e("",tipoGasto.tipoGasto)
        }
    }



    override fun getItemCount(): Int {
        return perfiles.size
    }


}