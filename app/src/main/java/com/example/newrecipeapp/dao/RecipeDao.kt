package com.example.newrecipeapp.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newrecipeapp.model.CategoryItem
import com.example.newrecipeapp.model.Favorite
import com.example.newrecipeapp.model.MealByCategoryItem
import com.example.newrecipeapp.model.MealDetails

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertAllCategory(categoryList: List<CategoryItem>)

    @Query("Delete from main_categories")
    fun deleteAllCategories()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertMealsList(mealItem: MealByCategoryItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun InsertMealDetails(mealDetails: MealDetails)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun AddFavorite(favorite: Favorite)

    @Query("select * from main_categories")
    suspend fun getAllCategories():List<CategoryItem>

    @Query("select * from MealByCategory where category=:category")
    suspend fun getMealsByCategory(category:String):List<MealByCategoryItem>

    @Query("select * from MealDetails where idMeal=:mealId")
    suspend fun getMealDetails(mealId:Int):MealDetails

    @Query("select isFavorite from Favorite where idMeal=:mealId")
    suspend fun getFavoriteStatus(mealId: Int): Boolean

}