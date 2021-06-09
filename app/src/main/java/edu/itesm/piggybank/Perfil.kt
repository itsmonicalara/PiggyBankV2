package edu.itesm.piggybank

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Perfil(val picture:Int,
                  val tipo :String) : Parcelable
