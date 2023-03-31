package com.example.eatfit.model.remote

import com.example.eatfit.model.core.RetrofitHelper
import com.example.eatfit.model.dto.ComplexSearchDTO
import com.example.eatfit.model.dto.GetRandomRecipesDTO
import com.example.eatfit.model.dto.Receipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class ReceipesRemoteDataSource @Inject constructor() {

    private val retrofit = RetrofitHelper.getRetrofit()
    private val apiKey = "0354b5e62cc34249800d0b5e41c5b29a"

    suspend fun findAllReceipes(): Response<GetRandomRecipesDTO> {
        return withContext(Dispatchers.IO){
            val query: Map<String, String> = mapOf(
                "apiKey" to apiKey,
                "number" to "10"
            )
            val response = retrofit.create(ReceipesApiClient::class.java).findAllReceipes(query)
            response
        }
    }

    suspend fun findByFilter(query: String): Response<ComplexSearchDTO> {

        return withContext(Dispatchers.IO){
            val queryRequest: Map<String, String> = mapOf(
                "apiKey" to apiKey,
                "number" to "10",
                "query" to query
            )

            val response = retrofit.create(ReceipesApiClient::class.java).findByFilter(queryRequest)
            response
        }
    }

    suspend fun findOneReceipe(id: Int): Response<Receipe> {
        return withContext(Dispatchers.IO){
            val queryRequest: Map<String, String> = mapOf(
                "apiKey" to apiKey,
            )

            val response = retrofit.create(ReceipesApiClient::class.java).findOneReceipe(id, queryRequest)
            response
        }
    }


}