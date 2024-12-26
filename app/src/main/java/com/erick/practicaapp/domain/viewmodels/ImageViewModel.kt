package com.erick.practicaapp.domain.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.erick.practicaapp.data.di.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class ImageUiState {
    data object Loading : ImageUiState()
    data class Success(val imageUrl: String) : ImageUiState()
    data class Error(val message: String) : ImageUiState()
    data object Initial : ImageUiState()
}

class ImageViewModel : ViewModel() {
    private val _uiState = MutableStateFlow<ImageUiState>(ImageUiState.Initial)
    val uiState: StateFlow<ImageUiState> = _uiState

    fun fetchImage() {
        viewModelScope.launch {
            try {
                _uiState.value = ImageUiState.Loading
                val response = ApiClient.retrofit.getProfileImage()
                _uiState.value = ImageUiState.Success(response.message) // Usamos message en lugar de imageUrl
            } catch (e: Exception) {
                _uiState.value = ImageUiState.Error(
                    e.message ?: "Error desconocido al cargar la imagen"
                )
            }
        }
    }
}