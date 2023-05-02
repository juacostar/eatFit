package com.example.eatfit.domain

import com.example.eatfit.model.RecipesRepository
import com.example.eatfit.model.dto.Receipe
import javax.inject.Inject

class findOneRecipeUseCase @Inject constructor(
    private val repository: RecipesRepository
) {

    suspend operator fun invoke(id: Int): Receipe = repository.findOneRecipe(id)
}