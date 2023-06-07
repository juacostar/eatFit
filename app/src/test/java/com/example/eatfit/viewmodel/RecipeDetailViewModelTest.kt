package com.example.eatfit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.eatfit.domain.findOneRecipeUseCase
import com.example.eatfit.model.dto.Receipe
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecipeDetailViewModelTest{

    @RelaxedMockK
    private lateinit var findOneRecipeUseCase: findOneRecipeUseCase

    private lateinit var recipeDetailViewModel: RecipeDetailViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        recipeDetailViewModel = RecipeDetailViewModel(findOneRecipeUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun testWithOneRecipe(){
        // given
        val recipe = Receipe(0,"","",0,"",false, 0, "", listOf())
        coEvery { findOneRecipeUseCase(0) } returns recipe
        // when
        recipeDetailViewModel.onCreate(0)
        // then
        assert(recipeDetailViewModel.recipe.value == recipe)

    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }
}