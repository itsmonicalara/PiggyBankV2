package edu.itesm.piggybank

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_settings.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        ajustes_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_settingsFragment_to_ajustesFragment)
        }
        privacidad_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_settingsFragment_to_privacidadFragment)
        }
        terminos_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_settingsFragment_to_terminosFragment)
        }
        nosotros_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_settingsFragment_to_nosotrosFragment)
        }

    }
}