package com.erick.practicaapp.data.response

data class LoginRequest(
    val username: String = "defaultUsername",
    val email: String,
    val password: String
)
