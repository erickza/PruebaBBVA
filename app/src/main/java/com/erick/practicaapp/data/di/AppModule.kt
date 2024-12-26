package com.erick.practicaapp.data.di

import com.erick.practicaapp.data.response.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "https://private-6eaf4c-examenbbva.apiary-mock.com/" // Cambia a la URL base de tu API

    val retrofit: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
