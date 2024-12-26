package com.erick.practicaapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erick.practicaapp.data.di.ApiClient
import com.erick.practicaapp.data.response.LoginRequest
import com.erick.practicaapp.data.response.LoginResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    fun login(nombre: String, email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = ApiClient.retrofit.login(LoginRequest(nombre, email, password))
                _loginResponse.value = response
            } catch (e: Exception) {
                // Manejar errores
                _loginResponse.value = null
            }
        }
    }
}

