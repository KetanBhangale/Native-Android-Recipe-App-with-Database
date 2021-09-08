package com.example.newrecipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MealByCategory")
data class MealByCategoryItem(
    @PrimaryKey
    val idMeal: String,
    val category: String,
    val strMeal: String,
    val strMealThumb: String
)