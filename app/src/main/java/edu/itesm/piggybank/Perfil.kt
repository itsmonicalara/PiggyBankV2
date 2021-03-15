package edu.itesm.piggybank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Perfil(val picture:Int,
                  val nombre:String,
                  val edad:String,
                  val historia: String) : Parcelable
