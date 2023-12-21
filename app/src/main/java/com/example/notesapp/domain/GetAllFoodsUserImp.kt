package com.example.newprojeckt.domian

import com.example.newprojeckt.data.repository.GetAllFoodsRepositoryImpl
import com.example.notesapp.data.model.FoodModel

class GetAllFoodsUserCaseImpl : GetAllFoodsUserCase {
    private val foodsRepository = GetAllFoodsRepositoryImpl()

    override suspend fun getAllFoods(): List<FoodModel> {
        return foodsRepository.getAllFoods()
    }
}