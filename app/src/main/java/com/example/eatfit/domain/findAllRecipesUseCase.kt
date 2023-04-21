package com.example.eatfit.domain

import com.example.eatfit.model.RecipesRepository
import com.example.eatfit.model.dto.Receipe
import javax.inject.Inject

class findAllRecipesUseCase  @Inject constructor(
    private val repository: RecipesRepository
){

    suspend operator fun invoke(): List<Receipe> = repository.findAllReceipes()
}