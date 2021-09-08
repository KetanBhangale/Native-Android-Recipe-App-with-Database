package com.example.newrecipeapp.database

import androidx.room.*
import com.example.newrecipeapp.dao.RecipeDao
import com.example.newrecipeapp.model.CategoryItem
import com.example.newrecipeapp.model.Favorite
import com.example.newrecipeapp.model.MealByCategoryItem
import com.example.newrecipeapp.model.MealDetails

@Database(
    entities = [CategoryItem::class, MealByCategoryItem::class, MealDetails::class, Favorite::class],
    version = 1, exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao
}