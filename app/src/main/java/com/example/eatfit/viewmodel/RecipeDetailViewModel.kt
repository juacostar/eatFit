package com.example.eatfit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatfit.domain.findOneRecipeUseCase
import com.example.eatfit.model.dto.Receipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailViewModel @Inject constructor(
    private val findOneRecipeUseCase: findOneRecipeUseCase
): ViewModel() {

    val recipe= MutableLiveData<Receipe>()
    val isLoading= MutableLiveData<Boolean>()

    fun onCreate(id: Int){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = findOneRecipeUseCase(id = id)
            recipe.postValue(result)
            isLoading.postValue(false)
        }
    }

}