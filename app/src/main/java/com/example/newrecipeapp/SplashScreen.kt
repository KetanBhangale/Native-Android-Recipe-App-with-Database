package com.example.newrecipeapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.newrecipeapp.databinding.ActivitySplashBinding
import com.example.newrecipeapp.viewmodel.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private var _binding: ActivitySplashBinding?=null
    val binding: ActivitySplashBinding get() = _binding!!
    private val recipeViewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding.root)
        _binding!!.next.setOnClickListener {
//            _binding!!.progressBar.visibility= View.VISIBLE
//            _binding!!.next.visibility = View.GONE
            val intent = Intent(this@SplashScreen, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}