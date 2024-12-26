package com.erick.practicaapp.data.response

import com.erick.practicaapp.data.Paths
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST(Paths.BASE_URL + Paths.LOGIN)
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @GET(Paths.IMAGE)
    suspend fun getProfileImage(): ProfileImageResponse
}
