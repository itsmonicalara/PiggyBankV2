package edu.itesm.piggybank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.SignInAccount
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.itesm.piggybank.databinding.FragmentRegisterBinding
import kotlinx.android.synthetic.main.fragment_register.*


class Register : Fragment() {

    private lateinit var bind : FragmentRegisterBinding
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bind = FragmentRegisterBinding.inflate(layoutInflater)
        // Inicializa objetos:

        auth = Firebase.auth
        crearCuenta.setOnClickListener { crearUsuario() }

    }

    override fun onStart() {
        super.onStart()
    }

    private fun usuarioCreado(){
        val builder = AlertDialog.Builder(this.requireContext())
        with(builder){
            Toast.makeText(this.context,"Usuario creado con éxito", Toast.LENGTH_LONG).show()
            view?.findNavController()?.navigate(R.id.firstFragment)
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
                    correo.text.clear()
                    contrasena.text.clear()
                }
            }.addOnFailureListener{
                Toast.makeText(this.context,it.toString(), Toast.LENGTH_LONG).show()
            }
        }else{Toast.makeText(this.context,"No dejes campos vacios", Toast.LENGTH_LONG).show()}
        }else{Toast.makeText(this.context,"Contraseñas no coinciden", Toast.LENGTH_LONG).show() }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }


}