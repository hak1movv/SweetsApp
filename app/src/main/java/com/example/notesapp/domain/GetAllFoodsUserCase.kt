package com.example.newprojeckt.domian

import com.example.notesapp.data.model.FoodModel

interface GetAllFoodsUserCase {
    suspend fun getAllFoods(): List<FoodModel>
}