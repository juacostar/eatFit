package com.example.eatfit.model.dto

import com.google.gson.annotations.SerializedName

data class ReceipeComplexSearch(

    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("imageType")
    val imageType: String
)
