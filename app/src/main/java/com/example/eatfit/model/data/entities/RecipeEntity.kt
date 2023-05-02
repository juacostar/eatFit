package com.example.eatfit.model.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "recipe")
data class RecipeEntity(
    @ColumnInfo(name = "id")
    val id: String,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "image")
    val image: String

)
