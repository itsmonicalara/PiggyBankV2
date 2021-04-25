package edu.itesm.piggybank

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_olvide_contrasena.*

class OlvideContrasena : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase : FirebaseFirestore
    private var clave = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_olvide_contrasena, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        auth = Firebase.auth
        dataBase = Firebase.firestore

        recuperar.setOnClickListener {getDataUser()}

    }


    fun getDataUser(){
        val capitalCities = dataBase.collection("users")
        capitalCities.get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    if(document.data.get("correo").toString() == recuperarContrasena.text.toString()){
                        Log.d(TAG, "existe")
                        clave = document.data.get("contrasena").toString()
                        Toast.makeText(this.context,clave, Toast.LENGTH_LONG).show()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }


    }


}