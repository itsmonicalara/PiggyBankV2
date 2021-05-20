package edu.itesm.piggybank

import android.content.ContentValues
import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.fragment_first.*
import androidx.appcompat.widget.Toolbar;
import com.google.firebase.auth.ktx.auth
import kotlinx.android.synthetic.main.fragment_olvide_contrasena.*
import kotlinx.android.synthetic.main.fragment_piggy.*
import kotlinx.android.synthetic.main.fragment_piggy.iniciar_boton

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters

    private val args by navArgs<FirstFragmentArgs>()
    private lateinit var dataBase : FirebaseFirestore
    private var clave = ""
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //toggle = ActionBarDrawerToggle(this.activity,drawerLayout,R.string.open, R.string.close)
        //drawerLayout.addDrawerListener(toggle)
        //toggle.syncState()
        //supportActionBar?.setDisplayHomeAsUpEnabled(true)



        dataBase = Firebase.firestore
        getDataUser()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        getDataUser()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //MENU
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nuevoProducto ->Toast.makeText(this.context,"nuevoProducto",Toast.LENGTH_SHORT).show()
                R.id.anadirDinero -> Toast.makeText(this.context,"anadirDinero",Toast.LENGTH_SHORT).show()
                R.id.metas -> Toast.makeText(this.context,"metas",Toast.LENGTH_SHORT).show()
                R.id.ajustes -> Toast.makeText(this.context,"ajustes",Toast.LENGTH_SHORT).show()
            }
            true
        }
        perfiles_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_perfilesFragment)
        }
        config_boton.setOnClickListener {
            view?.findNavController()?.navigate(R.id.action_firstFragment_to_settingsFragment)
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
                            nombrePerfilInicio.text = document.get("nombre") as CharSequence?
                            correo_text.text = document.get("correo") as CharSequence?
                            dinero_text.text = "$"+document.get("cochinito").toString()
                        }
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }

    }



}