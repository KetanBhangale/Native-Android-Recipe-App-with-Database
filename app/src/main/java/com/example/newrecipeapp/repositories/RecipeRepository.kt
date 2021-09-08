package com.example.newrecipeapp.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newrecipeapp.dao.RecipeDao
import com.example.newrecipeapp.model.*
import com.example.newrecipeapp.network.APIService
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeRepository @Inject constructor(private val apiService: APIService, private val recipeDao: RecipeDao) {

    init {
        getAndInsertCategoryInDB()
    }
    suspend fun getAllCategories(): List<CategoryItem> {
        return recipeDao.getAllCategories()
    }
    suspend fun getAllMealsByCategory(category: String): List<MealByCategoryItem>{
        return  recipeDao.getMealsByCategory(category)
    }

    suspend fun getMealDetails(mealId:Int): MealDetails{
       return recipeDao.getMealDetails(mealId)
        //return apiService.getMealDetails(mealId).meals[0]
    }

    suspend fun getFavorite(mealId: Int):Boolean{
        return recipeDao.getFavoriteStatus(mealId)
    }
    private fun getAndInsertCategoryInDB(){
        GlobalScope.launch {
            val catList = apiService.getAllCategories().categories
            if (catList.isNotEmpty()){
                //recipeDao.deleteAllCategories()
                recipeDao.InsertAllCategory(catList)
            }
        }
    }

    fun getAllInsertMealByCategoryInDB(category:String){
        GlobalScope.launch {
            //Log.i("main category", category)
            val mealList = apiService.getMealsByCategory(category).meals
            if (mealList != null) {
                for (meal in mealList) {
                    recipeDao.InsertMealsList(
                        MealByCategoryItem(
                            meal.idMeal,
                            category,
                            meal.strMeal,
                            meal.strMealThumb
                        )
                    )
                }
            }

        }
    }

    fun getAndInsertMealDetailsInDB(mealId:Int){
        GlobalScope.launch {
            val mealDetails = apiService.getMealDetails(mealId)
            //Log.i("main mealDetails", mealDetails.meals[0].toString())
            recipeDao.InsertMealDetails(mealDetails.meals[0])
        }
    }
    fun addFavorite(mealId:Int, flag:Boolean){
        GlobalScope.launch {
            recipeDao.AddFavorite(Favorite(mealId, flag))
        }
    }
}