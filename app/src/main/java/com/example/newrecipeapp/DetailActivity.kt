package com.example.newrecipeapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.newrecipeapp.databinding.ActivityDetailBinding
import com.example.newrecipeapp.databinding.ActivityHomeBinding
import com.example.newrecipeapp.model.MealDetails
import com.example.newrecipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    val binding: ActivityDetailBinding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    private lateinit var mealId: String
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mealId = intent.getStringExtra("mealId").toString()
        Log.i("main123", "main123=$mealId");
        recipeViewModel.getAndInsertMealDetailsInDB(mealId.toInt())
        recipeViewModel.getMealDetails(mealId.toInt())
        recipeViewModel.mealDetails.observe(this, Observer { meal->
            //Log.i("main from details",meal.toString())
            if (meal != null){
                setUpUI(meal)
            }else{
                recipeViewModel.getMealDetails(mealId.toInt())
            }

        })
        recipeViewModel.getFavorite(mealId.toInt())
        recipeViewModel.isFavorite.observe(this, Observer {  flag->
            if (flag !=null && flag){
                    _binding!!.imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg3)
                }else{
                    _binding!!.imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg2)
                }

        })




    }

    private fun setUpUI(mealDetails: MealDetails) {
        _binding!!.apply {
            Glide.with(this@DetailActivity).load(mealDetails.strMealThumb).into(imgItem)
            tvCategory.text = mealDetails.strMeal
            tvIngredients.text =
                        "${mealDetails.strIngredient1}\n" +
                        "${mealDetails.strIngredient2}\n" +
                        "${mealDetails.strIngredient3}\n" +
                        "${mealDetails.strIngredient4}\n" +
                        "${mealDetails.strIngredient5}\n" +
                        "${mealDetails.strIngredient6}\n" +
                        "${mealDetails.strIngredient7}\n" +
                        "${mealDetails.strIngredient8}\n"
            tvInstructions.text = mealDetails.strInstructions
            if (mealDetails.strYoutube != null){
                btnYoutube.visibility = View.VISIBLE
                btnYoutube.setOnClickListener {
                    val uri = Uri.parse(mealDetails.strYoutube)
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    startActivity(intent)
                }
            }else{
                btnYoutube.visibility = View.INVISIBLE
            }

            imgToolbarBtnBack.setOnClickListener {
                this@DetailActivity.finish()
            }
            imgToolbarBtnFav.setOnClickListener {
                Log.i("flag", ""+isFavorite)
                if(isFavorite){
                    recipeViewModel.AddFavoriteInDB(mealId.toInt(), false)
                    imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg2)
                    isFavorite = false
                }else{
                    isFavorite = true
                    recipeViewModel.AddFavoriteInDB(mealId.toInt(), true)
                    imgToolbarBtnFav.setBackgroundResource(R.drawable.btn_bg3)
                }

            }

        }
    }
}