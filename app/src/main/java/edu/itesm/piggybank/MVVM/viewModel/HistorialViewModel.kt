package edu.itesm.piggybank.MVVM.viewModel

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.itesm.piggybank.MVVM.model.HistorialModel
import edu.itesm.piggybank.MVVM.model.HistorialProvider
import kotlinx.android.synthetic.main.fragment_perfiles.*


class HistorialViewModel: ViewModel() {
    val historialModelo = MutableLiveData<HistorialModel>()
    private var clave = ""
    private lateinit var dataBase : FirebaseFirestore

    fun mensajeRandom(){
        val mensaje = HistorialProvider.getHistory()
        historialModelo.postValue(mensaje)
    }
    fun getDataUser(){
        dataBase = Firebase.firestore
        val reference = dataBase.collection("users")
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
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }

    }
    fun nuevaConfiguracion(plan: String){
        getDataUser()
         var clave = ""
        val user = Firebase.auth.currentUser
        user?.let {
            val email = user.email
        }
            val capitalCities = dataBase.collection("users")
            capitalCities.get()
                .addOnSuccessListener { documents ->
                    for (documentGot in documents) {
                        if(documentGot.data.get("correo").toString() == user.email.toString()){

                            var data1 =hashMapOf("plan" to plan)
                            dataBase.collection("users").document(documentGot.id.toString())
                                .set(data1, SetOptions.merge())
                        }
                    }

                } .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
    }

}