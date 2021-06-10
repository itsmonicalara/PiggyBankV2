package edu.itesm.piggybank

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import kotlinx.android.synthetic.main.fragment_perfiles.*
import java.io.ByteArrayOutputStream
import java.util.*
import kotlin.collections.ArrayList


class PerfilesFragment : Fragment() {
    private lateinit var dataBase : FirebaseFirestore
    private var clave = ""
    private  var cochinito = 0.0
    private val args by navArgs<PerfilesFragmentArgs>()
    private lateinit var operacion : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        operacion = args.operacion
        Log.e("",operacion)
        dataBase = Firebase.firestore
        val reference = dataBase.collection("users")
        getDataUser()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfiles, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        if (operacion == "mas"){
            signoDinero.text = "+"
        }else{
            signoDinero.text = "-"
        }
        perfiles_recycler.apply{
            layoutManager = LinearLayoutManager(activity)
            adapter = PerfilesAdapter(createData())
        }
        actualizarSaldo.setOnClickListener{
            nuevaConfiguracion()
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

    fun getDataUser(){
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
        }
        val capitalCities = dataBase.collection("users")
        capitalCities.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document.data.get("correo").toString() == user.email.toString()){
                        Log.d(ContentValues.TAG, "existe")
                        clave = document.data.get("correo").toString()
                        cochinito = document.data.get("cochinito").toString().toDouble()


                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }

    fun nuevaConfiguracion(){
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
        }
        if(editTextNumber != null){


                    val capitalCities = dataBase.collection("users")
                    capitalCities.get()
                        .addOnSuccessListener { documents ->
                            for (documentGot in documents) {
                                if(documentGot.data.get("correo").toString() == user.email.toString()){
                                    val data = hashMapOf("incrementos" to editTextNumber.text.toString())
                                    Log.e("base",cochinito.toString())
                                    Log.e("local",editTextNumber.text.toString())
                                    var data2: Any
                                    if(operacion == "mas"){
                                        data2 =hashMapOf("cochinito" to (  editTextNumber.text.toString().toDouble() + cochinito))
                                    }else{
                                        data2 =hashMapOf("cochinito" to (  cochinito - editTextNumber.text.toString().toDouble()  ))
                                    }

                                    dataBase.collection("users").document(documentGot.id.toString())
                                        .set(
                                            data,SetOptions.merge()
                                        )
                                    dataBase.collection("users").document(documentGot.id.toString())
                                        .set(
                                            data2,SetOptions.merge()
                                        )
                                }
                            }
                        } .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }


        }else{
            Toast.makeText(this.context,"Tienes que seleccionar una foto para poder actualizar", Toast.LENGTH_LONG).show()
        }


    }

}