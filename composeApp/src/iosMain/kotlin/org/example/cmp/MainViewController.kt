package org.example.cmp

import AppTheme
import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import navigation.MultiApp

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) {
    AppTheme {
        MultiApp()
    }
}