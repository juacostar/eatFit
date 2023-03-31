package com.example.eatfit.model.dto

import com.google.gson.annotations.SerializedName

data class Receipe(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("readyInMinutes")
    val readyInMinutes:Int,
    @SerializedName("sourceUrl")
    val sourceUrl: String,
    @SerializedName("vegetarian")
    val vegetarian: Boolean,
    @SerializedName("aggregateLikes")
    val aggregateLikes: Int,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("extendedIngredients")
    val extendedIngredients: List<ExtendedIngredient>






)
