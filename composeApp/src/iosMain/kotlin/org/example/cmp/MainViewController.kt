package org.example.cmp

import androidx.compose.ui.window.ComposeUIViewController
import di.initKoin
import di.initKoinIOS
import navigation.App

fun MainViewController() = ComposeUIViewController(
    configure = { initKoin() }
) { App() }