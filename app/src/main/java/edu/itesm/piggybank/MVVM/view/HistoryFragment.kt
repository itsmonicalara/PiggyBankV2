package edu.itesm.piggybank.MVVM.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.itesm.piggybank.MVVM.viewModel.HistorialViewModel
import edu.itesm.piggybank.databinding.FragmentHistoryBinding


class HistoryFragment : Fragment() {
    private lateinit var binding: FragmentHistoryBinding
    private lateinit  var historialViewModel: HistorialViewModel

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
            binding.textoRandom1.text = it.razon
            binding.textoRandom2.text = it.cantidad.toString()
        })
        binding.container.setOnClickListener{historialViewModel.mensajeRandom()}
        return binding.root
    }


}