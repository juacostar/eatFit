package com.example.eatfit.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eatfit.domain.findAllRecipesUseCase
import com.example.eatfit.model.dto.Receipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val findAllRecipesUseCase: findAllRecipesUseCase
): ViewModel() {

    val recipesList = MutableLiveData<List<Receipe>>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = findAllRecipesUseCase()
            recipesList.postValue(result)
            isLoading.postValue(false)
        }
    }
}