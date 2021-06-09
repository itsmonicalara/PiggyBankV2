package edu.itesm.piggybank.MVVM.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import edu.itesm.piggybank.MVVM.model.MensajesProvider
import edu.itesm.piggybank.MVVM.viewModel.MensajesViewModel
import edu.itesm.piggybank.R
import edu.itesm.piggybank.databinding.FragmentAunNoSeBinding


class AunNoSe : Fragment() {

    private lateinit var binding: FragmentAunNoSeBinding
    private lateinit  var mensajesViewModel : MensajesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAunNoSeBinding.inflate(inflater, container, false)
        mensajesViewModel = ViewModelProvider(this).get(MensajesViewModel::class.java)

        // Inflate the layout for this fragment

        mensajesViewModel.mensajesModelo.observe(viewLifecycleOwner, Observer {
            binding.textoRandom.text = it.mensaje
        })
        binding.container.setOnClickListener{mensajesViewModel.mensajeRandom()}
        return binding.root
    }







}