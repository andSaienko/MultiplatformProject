package org.example.cmp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import di.initKoin
import navigation.App

fun main() = application {
    initKoin()
    Window(
        onCloseRequest = ::exitApplication,
        title = "MultiplatformProject",
    ) {
        App()
    }
}