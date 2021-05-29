package edu.itesm.piggybank

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import edu.itesm.piggybank.databinding.FragmentAnadirProductoBinding
import kotlinx.android.synthetic.main.fragment_anadir_producto.*
import kotlinx.android.synthetic.main.fragment_piggy.*


class AnadirProducto : Fragment() {

    private val PICK_IMAGE = 100
    var imageUri: Uri? = null

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
            albumImage?.setImageURI(imageUri);
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