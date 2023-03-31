package com.example.eatfit.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class) // limit, application level in this case
object NetworkModule {
    // inject interfaces or libraries dependencies

    @Singleton // one instance
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}