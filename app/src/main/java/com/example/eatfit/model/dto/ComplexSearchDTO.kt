package com.example.eatfit.model.dto

import com.google.gson.annotations.SerializedName

data class ComplexSearchDTO(
    @SerializedName("results")
    val results:List<ReceipeComplexSearch>
)
