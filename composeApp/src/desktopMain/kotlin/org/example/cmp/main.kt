package org.example.cmp

import AppTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.initKoin
import navigation.MultiApp

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MultiplatformProject",
    ) {
        AppTheme {
            MultiApp()
        }
    }
}