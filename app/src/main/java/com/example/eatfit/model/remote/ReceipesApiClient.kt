package com.example.eatfit.model.remote

import com.example.eatfit.model.dto.ComplexSearchDTO
import com.example.eatfit.model.dto.GetRandomRecipesDTO
import com.example.eatfit.model.dto.Receipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap
import javax.inject.Inject

interface ReceipesApiClient {

    @GET("/recipes/random")
    suspend fun findAllReceipes(
        @QueryMap query: Map<String, String>
    ):Response<GetRandomRecipesDTO>

    @GET("/recipes/complexSearch")
    suspend fun findByFilter(
        @QueryMap query: Map<String, String>
    ): Response<ComplexSearchDTO>

    @GET("/recipes/{id}/information")
    suspend fun findOneReceipe(
        @Path("id") id:Int,
        @QueryMap query: Map<String, String>
    ): Response<Receipe>





}