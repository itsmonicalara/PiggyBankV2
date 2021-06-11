package edu.itesm.piggybank.MVP.presenter

import edu.itesm.piggybank.MVP.contract.Contract

class Presenter (
    private var mainView : Contract.View?,
    private var model : Contract.Model) : Contract.Presenter, Contract.Model.OnFinish {

    override fun onButtonClick() {
        model.getSiguienteMensaje(this)
    }

    override fun onFinished(mensaje: String) {
        if(mainView != null){
            mainView!!.setMensaje(mensaje)
        }
    }

}