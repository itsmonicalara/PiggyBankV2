package edu.itesm.piggybank

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_incrementos_vista.*
import kotlinx.android.synthetic.main.nav_header.*


class IncrementosVista : Fragment() {

    private lateinit var dataBase : FirebaseFirestore
    private lateinit var incAdapter: IncrementosAdapter
    private lateinit var incrementos: ArrayList<IncrementoBase>
    private var clave = ""
    private  var incrementosBase = hashMapOf<String, Double?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = Firebase.firestore


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_incrementos_vista, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataUser()
        initRecycler()
    }

    fun initRecycler(){
        incrementos = ArrayList<IncrementoBase>()
        incAdapter = IncrementosAdapter(incrementos)
        recycler_incremento.layoutManager = LinearLayoutManager(this.context)
        recycler_incremento.adapter = incAdapter
        for (item in 0..incrementosBase.size+1){
            Log.e("","item")
            val incremento = IncrementoBase("Cumpleaños",500)
            incrementos.add(incremento)
        }


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
                        incrementosBase = document.data.get("incremento") as HashMap<String, Double?>
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }


}