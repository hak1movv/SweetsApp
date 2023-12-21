package com.example.newprojeckt.presentation.main_screen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newprojeckt.domian.GetAllFoodsUserCaseImpl
import com.example.notesapp.data.model.FoodModel
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val getAllFoodsUseCase = GetAllFoodsUserCaseImpl()

    val foodLiveData: MutableLiveData<List<FoodModel>> = MutableLiveData()

    init {
        getAllFoods()
    }

    private fun getAllFoods() {
        viewModelScope.launch {
            val response = getAllFoodsUseCase.getAllFoods()
            foodLiveData.postValue(response)
        }
    }
}
