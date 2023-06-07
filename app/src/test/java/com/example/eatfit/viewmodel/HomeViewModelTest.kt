package com.example.eatfit.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.eatfit.domain.findAllRecipesUseCase
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
class HomeViewModelTest{

    @RelaxedMockK
    private lateinit var findAllRecipesUseCase: findAllRecipesUseCase

    private lateinit var homeViewModel: HomeViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        homeViewModel = HomeViewModel(findAllRecipesUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun gettingRecipesListTest(){
        // given
        val receipes = listOf<Receipe>(Receipe(0,"","",0,"",false, 0, "", listOf()))
        coEvery { findAllRecipesUseCase() } returns receipes
        // when
        homeViewModel.onCreate()
        // then
        assert(homeViewModel.recipesList.value == receipes)
    }


    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

}