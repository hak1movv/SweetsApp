package com.example.newprojeckt.presentation.adapter

import com.example.notesapp.data.model.FoodModel

interface FoodsItemClick{
    fun onFoodItemClick(foodModel: FoodModel)

    fun deleteAtFoodIndex(index: Int)

}