package navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.koin.compose.viewmodel.koinViewModel
import presentation.DetailsScreen
import presentation.HomeScreen
import presentation.SplashScreen

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = "splash"
        ) {
            composable("splash") {
                SplashScreen {
                    navController.navigate("home") {
                        popUpTo(0)
                    }
                }
            }
            composable("home") {
                HomeScreen { id ->
                    navController.navigate("details/$id")
                }
            }
            composable("details/{id}") {
                val id = it.arguments?.getString("id").orEmpty()
                DetailsScreen(id)
            }
        }
    }
}