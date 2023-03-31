package com.example.eatfit.model.dto

import com.google.gson.annotations.SerializedName

data class GetRandomRecipesDTO (
    @SerializedName("recipes")
    val recipes: List<Receipe>
)