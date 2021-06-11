package edu.itesm.piggybank.MVP.contract

interface Contract {

    interface View{
        fun setMensaje(mensaje:String)
    }

    interface Model{
        interface OnFinish{
            fun onFinished(mensaje: String)
        }
        fun getSiguienteMensaje(onFinishListener:OnFinish)
    }

    interface Presenter{
        fun onButtonClick()
    }
}