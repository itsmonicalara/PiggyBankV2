package edu.itesm.piggybank.MVVM.view

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.itesm.piggybank.MVVM.viewModel.HistorialViewModel
import edu.itesm.piggybank.R
import edu.itesm.piggybank.databinding.FragmentHistoryBinding
import kotlinx.android.synthetic.main.fragment_perfiles.*


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit  var historialViewModel: HistorialViewModel
    private lateinit var aplicarPlan: HistorialViewModel
    public var plan = "Básico"

    public var contador = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHistoryBinding.inflate(inflater, container, false)
        historialViewModel = ViewModelProvider(this).get(HistorialViewModel::class.java)

        // Inflate the layout for this fragment

        historialViewModel.historialModelo.observe(viewLifecycleOwner, Observer {
            binding.nombrePlan.text = it.nombre
            binding.precioPlan.text = "$ "+it.precio.toString()
            binding.caracteristicas.text = it.cararectistica
            binding.periodoPlan.text = it.periodo
        })
        binding.siguiente.setOnClickListener{historialViewModel.mensajeRandom()}
        binding.aplicar.setOnClickListener {
            plan = historialViewModel.historialModelo.value?.nombre.toString()
            historialViewModel.nuevaConfiguracion(plan)
        }
        return binding.root
    }





}