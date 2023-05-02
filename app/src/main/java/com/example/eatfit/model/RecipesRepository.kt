package com.example.eatfit.model

import androidx.appcompat.app.AppCompatActivity
import com.example.eatfit.model.core.RetrofitHelper
import com.example.eatfit.model.dto.Receipe
import com.example.eatfit.model.dto.ReceipeComplexSearch
import com.example.eatfit.model.remote.ReceipesRemoteDataSource
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import javax.inject.Inject

@AndroidEntryPoint
class RecipesRepository @Inject constructor(
    private val receipesRemoteDataSource: ReceipesRemoteDataSource
): AppCompatActivity() {

    suspend fun findAllReceipes(): List<Receipe> {
        return  receipesRemoteDataSource.findAllReceipes().body()!!.recipes
    }

    suspend fun findReceipesByFilter(query: String): List<ReceipeComplexSearch>{
        return receipesRemoteDataSource.findByFilter(query).body()!!.results
    }

    suspend fun findOneRecipe(id: Int): Receipe {
        return receipesRemoteDataSource.findOneReceipe(id).body()!!
    }

    suspend fun findAllLocalRecipes(){
        
    }


}