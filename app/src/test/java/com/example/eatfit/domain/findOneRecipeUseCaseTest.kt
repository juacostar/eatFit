package com.example.eatfit.domain

import com.example.eatfit.model.RecipesRepository
import com.example.eatfit.model.dto.Receipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class findOneRecipeUseCaseTest{

    @RelaxedMockK // garantee tthe creation of instance, mock doesn't
    private lateinit var recipesRepository: RecipesRepository

    private lateinit var getOneRecipeUseCase: findOneRecipeUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this) // initialization mock annotations
        getOneRecipeUseCase = findOneRecipeUseCase(recipesRepository)
    }

    @Test
    fun findOneRecipeWithCallingReturnsNothing() = runBlocking {

        // given
        val recipe = Receipe(0,"","",0,"",false, 0, "", listOf())
        coEvery { recipesRepository.findOneRecipe(0) } returns recipe
        // when
        val response = getOneRecipeUseCase(0)
        // then
        coVerify(exactly = 1) { recipesRepository.findOneRecipe(0) }

        assert(response == recipe)
    }


}