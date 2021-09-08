package com.example.newrecipeapp.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newrecipeapp.model.CategoryItem
import com.example.newrecipeapp.model.MealByCategoryItem
import com.example.newrecipeapp.model.MealDetails
import com.example.newrecipeapp.network.APIService
import com.example.newrecipeapp.repositories.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeRepository: RecipeRepository
) : ViewModel() {
    private var _categoryList = MutableLiveData<List<CategoryItem>>()
    val categoryList: LiveData<List<CategoryItem>> get() = _categoryList

    private var _mealList:MutableLiveData<List<MealByCategoryItem>> = MutableLiveData()
    val mealList:LiveData<List<MealByCategoryItem>> get() = _mealList

    private var _mealDetails = MutableLiveData<MealDetails>()
    val mealDetails: LiveData<MealDetails> get() = _mealDetails

    private var _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getAllCategories() {
        viewModelScope.launch {
            _categoryList.postValue(recipeRepository.getAllCategories())
        }

    }
    fun getAllMealsByCategory(category:String){
        viewModelScope.launch {
            //delay(3000)
            _mealList.postValue(recipeRepository.getAllMealsByCategory(category))
        }
    }

    fun getFavorite(mealId: Int){
        viewModelScope.launch {
            _isFavorite.postValue(recipeRepository.getFavorite(mealId))
        }
    }

//    fun getMealDetails(mealId: Int){
//        viewModelScope.launch {
//            _mealDetails.postValue(recipeRepository.getMealDetails(mealId))
//        }
//    }

    fun getAndInsertMealInDB(category:String){
        viewModelScope.launch {
            recipeRepository.getAllInsertMealByCategoryInDB(category)

        }
    }

    fun getAndInsertMealDetailsInDB(mealId:Int){
        viewModelScope.launch {
            recipeRepository.getAndInsertMealDetailsInDB(mealId)
        }
    }
    fun AddFavoriteInDB(mealId: Int, flag: Boolean){
        viewModelScope.launch{
            recipeRepository.addFavorite(mealId, flag)
        }
    }

    fun getMealDetails(mealId: Int){
        viewModelScope.launch {
            //delay(3000)
            _mealDetails.postValue(recipeRepository.getMealDetails(mealId))

        }
    }

}