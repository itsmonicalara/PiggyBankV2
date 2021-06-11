package edu.itesm.piggybank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*
import kotlin.collections.HashMap

@Parcelize
data class Usuario(
    var nombre: String,
    var correo: String,
    var cochinito: Int,
    var productosDeseado: HashMap<String, Double>,
    var incremento: HashMap<String, Double>,
    var decremento: HashMap<String, Double>,
    var plan: String
) : Parcelable

@Parcelize
data class ProductoDeseado(var producto: Producto, var cantidadAhorrada:Int, var fechaObtener: Date) : Parcelable

@Parcelize
data class Producto(var nombre: String, var categoria:String, var precio: Int, var fechaObtener: Date) : Parcelable

@Parcelize
data class Incrementos(var cantidad:Int, var motivo:String, var uso: String) : Parcelable