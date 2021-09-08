package com.example.newrecipeapp.Converters

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.newrecipeapp.model.MealByCategoryItem

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MealListConverter {
    @TypeConverter
    fun fromCategoryList(category: List<MealByCategoryItem>):String?{
        if (category == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object : TypeToken<MealByCategoryItem>(){

            }.type
            return gson.toJson(category,type)
        }
    }

    @TypeConverter
    fun toCategoryList ( categoryString: String):List<MealByCategoryItem>?{
        if (categoryString == null){
            return (null)
        }else{
            val gson = Gson()
            val type = object :TypeToken<MealByCategoryItem>(){

            }.type
            return  gson.fromJson(categoryString,type)
        }
    }
}