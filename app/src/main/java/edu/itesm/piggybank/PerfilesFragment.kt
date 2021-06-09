package edu.itesm.piggybank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_perfiles.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [PerfilesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PerfilesFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfiles, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        perfiles_recycler.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = PerfilesAdapter(createData())
        }
    }

    fun createData(): ArrayList<Perfil>{
        val perfiles = ArrayList<Perfil>()
        perfiles.add(Perfil(R.drawable.comida, "Comida"))
        perfiles.add(Perfil(R.drawable.compras, "Compras"))
        perfiles.add(Perfil(R.drawable.juguetes, "Juguetes"))
        perfiles.add(Perfil(R.drawable.tecnologia, "Tecnologia"))
        perfiles.add(Perfil(R.drawable.inversion, "Inversiones"))
        perfiles.add(Perfil(R.drawable.domingo, "Domingo"))
        perfiles.add(Perfil(R.drawable.cumpleanos, "Cumpleaños"))
        return perfiles
    }

}