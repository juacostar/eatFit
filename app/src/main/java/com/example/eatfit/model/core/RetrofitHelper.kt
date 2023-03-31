package com.example.eatfit.model.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    // object for instantiate the api client

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
}