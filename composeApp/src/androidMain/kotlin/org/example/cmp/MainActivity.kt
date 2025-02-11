package org.example.cmp

import AppTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.defaultComponentContext
import navigation.MultiApp
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class MainActivity : ComponentActivity() {

    private val modules = module {
        single<ComponentContext> { defaultComponentContext() }
    }

    init {
        loadKoinModules(modules)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            AppTheme {
                MultiApp()
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    AppTheme { MultiApp() }
}