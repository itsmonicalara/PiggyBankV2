package edu.itesm.piggybank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Usuario(val nombre: String, val correo:String, val contrasena:String, val cochinito: Int, val productosDeseado: List, val incrementos: ArrayList<Any>) : Parcelable