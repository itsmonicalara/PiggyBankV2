package edu.itesm.piggybank

import android.app.Activity.RESULT_OK
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_editar_perfil.*
import java.io.ByteArrayOutputStream
import java.util.*


class EditarPerfilFragment : Fragment() {

    private lateinit var dataBase : FirebaseFirestore
    private val RICapture = 1007
    private lateinit var foto: Bitmap
    private var clave = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBase = Firebase.firestore
        val reference = dataBase.collection("users")
        getDataUser()


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fotoUsuarioEditar.setOnClickListener {fotoUsuario()}
        guardarConfigNueva.setOnClickListener{nuevaConfiguracion()}
    }

    fun fotoUsuario(){
        val tomaFoto = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(tomaFoto,RICapture)
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
                        nombreEditar.setText(document.get("nombre") as CharSequence?)
                        val fotoRegreso = document.get("fotoPerfil")


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
        if(foto != null && cambiarContrasena.text.toString() != null && nombreEditar.text.toString() != null ){
            val baos = ByteArrayOutputStream()
            foto.compress(Bitmap.CompressFormat.JPEG,100,baos)
            val data = baos.toByteArray()
            val fileName = UUID.randomUUID().toString()
            val referenceStorage = FirebaseStorage.getInstance().getReference("/fotosUsuarios/$fileName")
            val upload = referenceStorage.putBytes(data)

            upload.addOnSuccessListener {
                referenceStorage.downloadUrl.addOnSuccessListener {
                    Toast.makeText(this.context,it.toString(), Toast.LENGTH_LONG).show()
                    val capitalCities = dataBase.collection("users")
                    capitalCities.get()
                        .addOnSuccessListener { documents ->
                            for (documentGot in documents) {
                                if(documentGot.data.get("correo").toString() == user.email.toString()){
                                    val data = hashMapOf("fotoPerfil" to it.toString(), "nombre" to nombreEditar.text.toString())
                                    dataBase.collection("users").document(documentGot.id.toString())
                                        .set(data, SetOptions.merge())
                                    cambiarContrasena()
                                    view?.findNavController()?.navigate(R.id.editarAPrimero)
                                }
                            }
                        } .addOnFailureListener { exception ->
                            Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                        }
                }
            }
        }else{
            Toast.makeText(this.context,"Tienes que seleccionar una foto para poder actualizar", Toast.LENGTH_LONG).show()
        }


    }

    fun cambiarContrasena(){
        val user = Firebase.auth.currentUser
        var newPassword = cambiarContrasena.text.toString();
        user.updatePassword(newPassword).addOnSuccessListener {
            Toast.makeText(this.context,"Usuario actualizado", Toast.LENGTH_LONG).show()
        }.addOnFailureListener{ exception ->
            Log.w(ContentValues.TAG, "Error", exception)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RICapture && resultCode == RESULT_OK){
            foto = data?.extras?.get("data") as Bitmap
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_editar_perfil, container, false)
    }


}