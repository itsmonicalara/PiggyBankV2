package edu.itesm.piggybank.MVP.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import edu.itesm.piggybank.MVP.contract.Contract
import edu.itesm.piggybank.MVP.model.Model
import edu.itesm.piggybank.MVP.presenter.Presenter
import edu.itesm.piggybank.R

class ViewActivity : AppCompatActivity(), Contract.View {

    private var textView : TextView? = null
    private var button : Button? = null

    private var presenter: Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)
        textView = findViewById(R.id.textView)
        button = findViewById(R.id.button)

        presenter = Presenter(this, Model())

        button!!.setOnClickListener {
            presenter!!.onButtonClick()
        }
    }

    override fun setMensaje(mensaje: String) {
        textView!!.text = mensaje
    }
}