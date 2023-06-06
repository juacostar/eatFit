package com.example.eatfit.domain

import com.example.eatfit.model.RecipesRepository
import com.example.eatfit.model.dto.Receipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class findAllRecipesUseCaseTest{ // select class, Navigate - test tp create the testing class

    @RelaxedMockK // garantee tthe creation of instance, mock doesn't
    private lateinit var recipesRepository: RecipesRepository

    private lateinit var getAllRecipesUseCase: findAllRecipesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getAllRecipesUseCase = findAllRecipesUseCase(recipesRepository)
    }

    @Test
    fun whenCallAllTheRecipesFromRemoteResourceAndReturnNothing() = runBlocking {

        // given
        coEvery { recipesRepository.findAllReceipes() } returns emptyList() // coevery becuse it's a routine
        //when
        getAllRecipesUseCase()
        // then
        coVerify(exactly = 1) { recipesRepository.findAllReceipes() }
    }

    @Test
    fun whenCallAllTheRecipesFromRemoteResourceAndReturnSomething() = runBlocking {

        // given
        val receipes = listOf<Receipe>(Receipe(0,"","",0,"",false, 0, "", listOf()))
        coEvery { recipesRepository.findAllReceipes() } returns  receipes// coevery becuse it's a routine
        //when
        val response = getAllRecipesUseCase()
        // then
        coVerify(exactly = 1) {
            recipesRepository.findAllReceipes()
        }

        assert(receipes == response)
    }


}