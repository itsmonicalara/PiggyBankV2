package edu.itesm.piggybank

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import edu.itesm.piggybank.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*


class Register : Fragment() {

    private lateinit var bind : FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var dataBase : FirebaseFirestore
    private lateinit var nuevoUsuario: Usuario
    lateinit var correoMandar : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind = FragmentRegisterBinding.inflate(layoutInflater)
        // Inicializa objetos:
        nuevoUsuario = Usuario("","",0, hashMapOf<String, Double>(), hashMapOf<String, Double>(), hashMapOf<String,Double>(), "")
        auth = Firebase.auth
        dataBase = Firebase.firestore
        crearCuenta.setOnClickListener { crearUsuario() }

    }

    override fun onStart() {
        super.onStart()
    }

    private fun usuarioCreado(){
        nuevoUsuario.nombre =  nombreUsuario.text.toString()
        nuevoUsuario.correo = correo.text.toString()
        correoMandar = correo.text.toString()

        dataBase.collection("users")
                .add(nuevoUsuario)
                .addOnSuccessListener { documentReference ->
                    Log.d("", "DocumentSnapshot added with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("", "Error adding document", e)
                }
        val builder = AlertDialog.Builder(this.requireContext())
        with(builder){
            Toast.makeText(this.context,"Usuario creado con éxito", Toast.LENGTH_LONG).show()
            val action = RegisterDirections.registerToLogin(correoMandar)
            view?.findNavController()?.navigate(action)
        }
    }

    fun crearUsuario(){
        if (contrasena.text.toString() == corroborarContrasena.text.toString()){

        if (correo.text.isNotEmpty() && contrasena.text.isNotEmpty() && nombreUsuario.text.isNotEmpty()){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(
                correo.text.toString(),
                contrasena.text.toString()
            ).addOnCompleteListener{
                if(it.isSuccessful){
                    usuarioCreado()
                    iniciarSesion()
                    correo.text.clear()
                    contrasena.text.clear()
                }
            }.addOnFailureListener{
                Toast.makeText(this.context,it.toString(), Toast.LENGTH_LONG).show()
            }
        }else{Toast.makeText(this.context,"No dejes campos vacios", Toast.LENGTH_LONG).show()}
        }else{Toast.makeText(this.context,"Contraseñas no coinciden", Toast.LENGTH_LONG).show() }
    }

    fun iniciarSesion(){
            FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    correo.text.toString(),
                    contrasena.text.toString()
            ).addOnCompleteListener{
            }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


}