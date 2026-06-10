package com.seufilme.app.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Isso garante que o app usa sempre a mesma conexão com a API
object RetrofitClient {

    // URL base da nossa API
    private const val BASE_URL = "http://10.0.2.2:8000/"

    // Cria a instância do Retrofit com a URL base
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}