package com.example.newrecipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Main_Categories")
data class CategoryItem(
    @PrimaryKey
    val idCategory: String,
    val strCategory: String,
    val strCategoryDescription: String,
    val strCategoryThumb: String
)