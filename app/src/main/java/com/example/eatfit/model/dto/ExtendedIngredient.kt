package com.example.eatfit.model.dto

import com.google.gson.annotations.SerializedName

data class ExtendedIngredient(
    @SerializedName("id")
    val id: Int,
    @SerializedName("original")
    val original: String,
    @SerializedName("originalName")
    val originalName: String
)
