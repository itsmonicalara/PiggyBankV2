package edu.itesm.piggybank

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class PerfilesAdapter(private val perfiles : List<Perfil>)
    : RecyclerView.Adapter<PerfilesAdapter.PerfilViewHolder>(){

    inner class PerfilViewHolder(renglon: View) : RecyclerView.ViewHolder(renglon){
        var nombre = renglon.findViewById<TextView>(R.id.nombre)
        var edad = renglon.findViewById<TextView>(R.id.edad)
        var historia = renglon.findViewById<TextView>(R.id.descripcion)
        var foto = renglon.findViewById<ImageView>(R.id.foto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfilViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val renglon_vista = inflater.inflate(R.layout.perfiles_renglon,parent, false)
        return PerfilViewHolder(renglon_vista)
    }

    override fun onBindViewHolder(holder: PerfilViewHolder, position: Int) {
        val perfil= perfiles[position]
        holder.foto.setImageResource(perfil.picture)
        holder.nombre.text = perfil.nombre
        holder.edad.text = perfil.edad
        holder.historia.text = perfil.historia
        holder.itemView.setOnClickListener(){
            val action = PerfilesFragmentDirections.actionPerfilesFragmentToPerfilFragment()
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return perfiles.size
    }


}