package com.example.newrecipeapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Favorite")
data class Favorite(
    @PrimaryKey
    val idMeal:Int,
    val isFavorite: Boolean
) {
}