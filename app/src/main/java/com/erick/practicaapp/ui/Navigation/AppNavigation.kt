package com.erick.practicaapp.ui.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.erick.practicaapp.domain.viewmodels.SessionManager
import com.erick.practicaapp.ui.screens.DetailsScreen
import com.erick.practicaapp.ui.screens.LoginScreen

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object Details : Screen("details/{name}/{lastName}/{gender}/{age}/{id}") {
        fun createRoute(name: String, lastName: String, gender: String, age: Int, id: String) =
            "details/$name/$lastName/$gender/$age/$id"
    }
}

@Composable
fun AppNavigation(sessionManager: SessionManager) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = if (sessionManager.isLoggedIn()) Screen.Details.createRoute(
            sessionManager.getUserName(),
            sessionManager.getLastName(),
            sessionManager.getGender(),
            sessionManager.getAge(),
            sessionManager.getUserId()
        ) else Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { name, lastName, gender, age, id ->
                    sessionManager.setUserData(name, lastName, gender, age, id)
                    navController.navigate(
                        Screen.Details.createRoute(name, lastName, gender, age, id)
                    ) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(
            Screen.Details.route,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("lastName") { type = NavType.StringType },
                navArgument("gender") { type = NavType.StringType },
                navArgument("age") { type = NavType.IntType },
                navArgument("id") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailsScreen(
                nombre = backStackEntry.arguments?.getString("name") ?: "",
                apellido = backStackEntry.arguments?.getString("lastName") ?: "",
                genero = backStackEntry.arguments?.getString("gender") ?: "",
                edad = backStackEntry.arguments?.getInt("age") ?: 0,
                id = backStackEntry.arguments?.getString("id") ?: "",
                sessionManager = sessionManager,
                onLogout = {
                    sessionManager.clearSession()
                    navController.navigate(Screen.Login.route) {
                        popUpTo(0) { inclusive = true }
                    }
                }
            )
        }
    }
}





