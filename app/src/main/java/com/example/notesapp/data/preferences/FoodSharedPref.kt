package com.example.newprojeckt.data.fake_data

import android.content.Context
import com.example.notesapp.data.model.FoodModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

private const val FOOD_SHARED_PREFENKES_KEY = "FOOD_SHARED_PREFENKES_KEY"
private const val FOOD_PREF_KEY = "FOOD__PREF_KEY"

class FoodSharedPref(
    private val context: Context,
) {
    private val sharedPreferences = context.getSharedPreferences(
        FOOD_SHARED_PREFENKES_KEY, Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveFood(foodModel: FoodModel) {
        val notes = getAllSavedFood().toMutableList()
        notes.add(0, foodModel)
        val notesGson = Gson().toJson(notes)
        sharedPreferences
            .edit()
            .putString(FOOD_PREF_KEY, notesGson)
            .apply()
    }

    fun getAllSavedFood(): List<FoodModel> {
        val json = sharedPreferences.getString(FOOD_PREF_KEY, null)
        val type: Type = object : TypeToken<List<FoodModel?>?>() {}.type
        val foodList = gson.fromJson<List<FoodModel>>(json, type)
        return foodList ?: emptyList()
    }
    fun deleteFoodAtIndex(index: Int) {
        val getAllNotes = getAllSavedFood().toMutableList()
        if (index in 0 until getAllNotes.size) {
            getAllNotes.removeAt(index)
            val noteGsonString = Gson().toJson(getAllNotes)
            sharedPreferences.edit().putString(FOOD_PREF_KEY, noteGsonString).apply()
        }
    }

    fun deleteAllFood() {
        sharedPreferences.edit().clear().apply()
    }
}