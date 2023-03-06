package com.example.dreamcars

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Car(
    val photo: Int,
    val name: String,
    val price: String,
    val fuel: String,
    val engine: String,
    val passengers: String,
    val description: String
): Parcelable