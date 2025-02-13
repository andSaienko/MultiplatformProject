package navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import domain.model.LoginType
import presentation.auth.AuthScreen
import presentation.details.DetailsScreen
import presentation.home.HomeScreen

@Composable
fun MultiApp(
    navController: NavHostController = rememberNavController(),
) {
    MultiAppNavHost(
        navController = navController
    )
}

@Composable
private fun MultiAppNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = "auth"
    ) {
        composable(route = "auth") {
            AuthScreen { loginType ->
                navController.navigate(
                    when (loginType) {
                        LoginType.Unverified -> "home"

                        is LoginType.Verified -> "home?userId=${loginType.id}"
                    }
                ) {
                    popUpTo(0)
                }
            }
        }
        composable(
            route = "home?userId={userId}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    nullable = true
                })
        ) {
            HomeScreen { id ->
                navController.navigate("details/$id")
            }
        }
        composable(route = "details/{itemId}") {
            DetailsScreen {
                navController.navigateUp()
            }
        }
    }
}