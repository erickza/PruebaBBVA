package com.erick.practicaapp.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.erick.practicaapp.domain.viewmodels.ImageUiState
import com.erick.practicaapp.domain.viewmodels.ImageViewModel
import com.erick.practicaapp.domain.viewmodels.SessionManager

@Composable
fun DetailsScreen(
    nombre: String,
    apellido: String,
    genero: String,
    edad: Int,
    id: String,
    sessionManager: SessionManager,
    onLogout: () -> Unit
) {
    val imageViewModel: ImageViewModel = viewModel()
    val uiState by imageViewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        imageViewModel.fetchImage()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is ImageUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.size(50.dp)
                )
            }
            is ImageUiState.Success -> {
                Image(
                    painter = rememberAsyncImagePainter((uiState as ImageUiState.Success).imageUrl),
                    contentDescription = "Foto de perfil",
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
            }
            is ImageUiState.Error -> {
                Text(
                    text = (uiState as ImageUiState.Error).message,
                    color = Color.Red,
                    textAlign = TextAlign.Center
                )
            }
            ImageUiState.Initial -> {

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Nombre: $nombre", fontSize = 18.sp)
        Text("Apellido: $apellido", fontSize = 18.sp)
        Text("Género: $genero", fontSize = 18.sp)
        Text("Edad: $edad", fontSize = 18.sp)
        Text("ID: $id", fontSize = 18.sp)
        Button(
            onClick = onLogout,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 16.dp)
        ) {
            Text("Cerrar Sesión")
        }
    }
}




