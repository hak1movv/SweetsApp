package com.example.notesapp.data.model

import java.io.Serializable

data class FoodModel(
    val foodName: String,
    val foodDescription: String,
    val foodPrice: String,
    val foodImg: String,

    ): Serializable