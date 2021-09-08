package com.example.newrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newrecipeapp.Adapters.IMainCategoryAdapter
import com.example.newrecipeapp.Adapters.IMealAdapter
import com.example.newrecipeapp.Adapters.MainCategoryAdapter
import com.example.newrecipeapp.Adapters.MealListAdapter
import com.example.newrecipeapp.databinding.ActivityHomeBinding
import com.example.newrecipeapp.databinding.ActivitySplashBinding
import com.example.newrecipeapp.model.CategoryItem
import com.example.newrecipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity(), IMainCategoryAdapter, IMealAdapter {
    private var _binding: ActivityHomeBinding?=null
    val binding: ActivityHomeBinding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var mainCategoryAdapter: MainCategoryAdapter
    private lateinit var mealListAdapter:MealListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpMainCategoryRV()
        setUpMealListRV()
        recipeViewModel.getAllCategories()
        recipeViewModel.categoryList.observe(this, Observer { list->
            Log.i("main from DB", list.size.toString())
            if (list.isEmpty()){
                recipeViewModel.getAllCategories()
            }
            mainCategoryAdapter.categoryList = list
            if (list.isNotEmpty()) {
                callListOfMeals(list[0].strCategory)
            }
        })
        //

//        recipeViewModel.getAndInsertMealDetailsInDB(52772)
//        recipeViewModel.getMealDetails(52772)
//        recipeViewModel.mealDetails.observe(this, Observer { meal->
//            Log.i("main MealDetails DB", meal.toString())
//        })
    }

    private fun setUpMainCategoryRV() {
        mainCategoryAdapter = MainCategoryAdapter(this)
        _binding!!.recyclerViewMain.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = mainCategoryAdapter
        }
    }

    override fun onCategoryClick(category: String) {
        callListOfMeals(category)
    }

    private fun callListOfMeals(category: String) {
        recipeViewModel.getAndInsertMealInDB(category)
        recipeViewModel.getAllMealsByCategory(category)
        _binding!!.categoryName.text = category
        recipeViewModel.mealList.observe(this, Observer { meal->
            Log.i("main Meal from DB", meal.toString())
            if (meal.isEmpty()){
                _binding!!.noDataText.visibility = View.VISIBLE
            }else {
                _binding!!.noDataText.visibility = View.GONE
            }
            mealListAdapter.mealList = meal
            mealListAdapter.notifyDataSetChanged()

        })

    }
    private fun setUpMealListRV() {
        mealListAdapter = MealListAdapter(this)
        _binding!!.recyclerViewCat.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity, LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = mealListAdapter
        }
    }

    override fun onMealItemClicked(mealId: String) {
        val intent = Intent(this@HomeActivity, DetailActivity::class.java)
        intent.putExtra("mealId", mealId)
        startActivity(intent)
    }


}