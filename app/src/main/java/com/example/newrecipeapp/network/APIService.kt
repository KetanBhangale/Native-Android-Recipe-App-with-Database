package com.example.newrecipeapp.network

import com.example.newrecipeapp.model.Category
import com.example.newrecipeapp.model.Meal
import com.example.newrecipeapp.model.MealByCategory
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("categories.php")
    suspend fun getAllCategories(): Category

    @GET("filter.php")
    suspend fun getMealsByCategory(@Query("i") i: String): MealByCategory

    @GET("lookup.php")
    suspend fun getMealDetails(@Query("i") i: Int): Meal
}