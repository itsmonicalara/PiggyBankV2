package edu.itesm.piggybank

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions
import edu.itesm.piggybank.databinding.FragmentAnadirProductoBinding
import kotlinx.android.synthetic.main.fragment_anadir_producto.*
import kotlinx.android.synthetic.main.fragment_piggy.*
import java.io.IOException


class AnadirProducto : Fragment() {

    private val PICK_IMAGE = 100
    var imageUri: Uri? = null
    var imageML: InputImage? = null
    val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

    private var binding: FragmentAnadirProductoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun openGallery() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, PICK_IMAGE)
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
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}