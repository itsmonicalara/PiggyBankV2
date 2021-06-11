package edu.itesm.piggybank

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_metas.*
import kotlinx.android.synthetic.main.nav_header.*
import java.util.HashMap

class MetasFragment : Fragment() {

    private lateinit var dataBase : FirebaseFirestore
    private var emailInfo = ""
    private var productTest = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = Firebase.firestore
        getDataUser()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_metas, container, false)
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
                        emailInfo = document.data.get("correo").toString()
                        Log.d("Email: ", emailInfo)
                        productTest = document.data.get("productosDeseado").toString()
                        Log.d("Lista de productos: ", productTest)
                        productoText.text = document.get("productosDeseado").toString()
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }

}