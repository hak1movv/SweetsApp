package com.example.newprojeckt.data.repository

import GetAllFoodsRepository

import com.example.notesapp.data.model.FoodModel
import com.example.notesapp.data.preferences.GetFakeData

class GetAllFoodsRepositoryImpl : GetAllFoodsRepository {
    override suspend fun getAllFoods(): List<FoodModel> = GetFakeData()

}







