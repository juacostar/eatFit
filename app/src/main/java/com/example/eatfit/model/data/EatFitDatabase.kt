package com.example.eatfit.model.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eatfit.model.data.DAO.RecipeDAO
import com.example.eatfit.model.data.entities.RecipeEntity

// version for migrations is very important
@Database(entities = [RecipeEntity::class],
version = 1)
abstract class EatFitDatabase: RoomDatabase() {

    abstract fun getRecipeDao():RecipeDAO
}