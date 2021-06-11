package edu.itesm.piggybank

import android.app.Activity.RESULT_OK
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import edu.itesm.piggybank.databinding.FragmentAnadirProductoBinding
import kotlinx.android.synthetic.main.fragment_anadir_producto.*
import kotlinx.android.synthetic.main.fragment_perfiles.*
import java.io.IOException
import java.util.*


class AnadirProducto : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private val PICK_IMAGE = 100
    var imageUri: Uri? = null
    var imageML: InputImage? = null
    val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

    private var binding: FragmentAnadirProductoBinding? = null
    private lateinit var dataBase : FirebaseFirestore
    private var emailUsuario = ""
    private var selectedDate = ""
    private var listaProductos = HashMap<Any,Any>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBase = Firebase.firestore
        getDataUser()
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Use the current date as the default date in the picker
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        // Create a new instance of DatePickerDialog and return it
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        selectedDate = dayOfMonth.toString() + " / " + (month + 1) + " / " + year
        Log.d("Selected Date", selectedDate)
    }

    fun showDatePickerDialog() {
        val newFragment = AnadirProducto()
        newFragment.show(getParentFragmentManager(), "datePicker")
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
                        emailUsuario = document.data.get("correo").toString()
                        listaProductos = document.data.get("productosDeseado") as HashMap<Any, Any>
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
        val capitalCities = dataBase.collection("users")
        capitalCities.get()
            .addOnSuccessListener { documents ->
                for (documentGot in documents) {
                    if(documentGot.data.get("correo").toString() == user.email.toString()){
                        var testText = productTextInputEditText.getText()
                        Log.d("Nombre: " , testText.toString())
                        var testText3 = priceTextInputEditText.getText()
                        Log.d("Precio: " , testText3.toString())
                        listaProductos.put(productTextInputEditText.text.toString(), priceTextInputEditText.text.toString().toDouble())
                        var data1 =hashMapOf("productosDeseado" to listaProductos)
                        dataBase.collection("users").document(documentGot.id.toString())
                            .set(data1, SetOptions.merge())
                    }
                }
            } .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
                }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            if (data != null) {
                imageUri = data.getData()
            };
            Glide.with(this)
                .load(imageUri)
                .override(400,350)
                .into(albumImage)
            //Create InputImage object from Uri
            try {
                imageML = InputImage.fromFilePath(context, imageUri)
            } catch (e: IOException) {
                e.printStackTrace()
            }
            //Configure the labeler and pass the InputImage
            labeler.process(imageML)
                .addOnSuccessListener { labels ->
                    for (label in labels) {
                        val text = label.text
                        val confidence = label.confidence
                        val index = label.index

                        mLText.setText("Este producto puede ser: " +text)
                        Log.d(confidence.toString(),"Confidence")
                        Log.d(index.toString(),"Index")
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(context, "No funciono", Toast.LENGTH_LONG).show()
                }



        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnadirProductoBinding.inflate(inflater, container, false)
        val categories = resources.getStringArray(R.array.categoria)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.list_item, categories)
        binding!!.autoCompleteTextView3.setAdapter(arrayAdapter)
        binding!!.buscarButton.setOnClickListener {
            openGallery()
        }
        binding!!.dateMeta.setOnClickListener {
            showDatePickerDialog()
        }
        binding!!.agregarButton.setOnClickListener {
            nuevaConfiguracion()
            var testText2 = autoCompleteTextView3.getText()
            Log.d("Categoria: " , testText2.toString())

            dateMeta.setText(selectedDate)
            var testText4 = dateMeta.getText()
            Log.d("Fecha: " , testText4.toString())
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }




}