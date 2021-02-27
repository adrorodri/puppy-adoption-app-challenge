package com.example.androiddevchallenge

import androidx.annotation.DrawableRes

data class Puppy(
    @DrawableRes val imageRes: Int,
    val name: String,
    val ageMonths: Int,
    val breed: String
) {
    var selected: Boolean = false
}