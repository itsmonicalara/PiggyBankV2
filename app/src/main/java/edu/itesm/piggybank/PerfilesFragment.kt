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
        perfiles.add(Perfil(R.drawable.duvall, "Shelley Duvalle", "71", "Shelley Alexis Duvall (n. Houston, Texas; 7 de julio de 1949) es una actriz retirada de cine y televisión, productora, escritora y cantante retirada estadounidense. "))
        perfiles.add(Perfil(R.drawable.desire, "Shelley Winters", "Fallecida", "Shirley Schrift, más conocida como Shelley Winters (San Luis, Misuri, 18 de agosto de 1920-Beverly Hills, California, 14 de enero de 2006"))
        perfiles.add(Perfil(R.drawable.gary, "Gary Lockwood", "Fallecido", "Gary Lockwood es un actor estadounidense nacido en 1937, conocido fundamentalmente por sus interpretaciones del astronauta Frank Poole en la película 2001"))
        return perfiles
    }

}