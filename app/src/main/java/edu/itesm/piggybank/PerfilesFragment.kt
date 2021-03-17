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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        perfiles_recycler.apply{
            layoutManager = LinearLayoutManager(activity)
            // set the custom adapter to the RecyclerView
            adapter = PerfilesAdapter(createData())
        }
    }

    fun createData(): ArrayList<Perfil>{
        val perfiles = ArrayList<Perfil>()
        perfiles.add(Perfil(R.drawable.boy1_, "Billy Rodriguez", "10", "Me gustaría ahorrar para comprarme una bici."))
        perfiles.add(Perfil(R.drawable.girl1_, "Ana Perez", "12", "Quiero un iPad y por eso ahorrare mis domingos."))
        perfiles.add(Perfil(R.drawable.girl2_, "Paola Diaz", "11", "Todos mis ahorros van a ser para mi viaje a Disney."))
        perfiles.add(Perfil(R.drawable.boy2_, "Tommy Sanchez", "8", "Solo quiero ahorrar."))
        return perfiles
    }

}