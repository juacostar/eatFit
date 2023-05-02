package com.example.eatfit.model.data.DAO

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eatfit.model.data.entities.RecipeEntity

@Dao
interface RecipeDAO {

    // suspend by corroutines
    @Query("select * from recipe")
    suspend fun findAllRecipes():List<RecipeEntity>

    // on case of conflict what have to do
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllRecipes(recipes: List<RecipeEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecipe(recipe: RecipeEntity)

}