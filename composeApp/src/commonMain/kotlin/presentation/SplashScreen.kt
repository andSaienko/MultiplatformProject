package presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay
import multiplatformproject.composeapp.generated.resources.Res
import multiplatformproject.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SplashScreen(navigateToHome: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(1000L)
        navigateToHome.invoke()
    }

    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(Res.drawable.compose_multiplatform),
        contentDescription = null
    )
}