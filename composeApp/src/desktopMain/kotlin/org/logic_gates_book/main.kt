package org.logic_gates_book

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "logic_gates_book_AIDW",
    ) {
        val hapticFeedback = HapticFeedback()
        val uri = Uri("https://github.com/Quickness-student/logic_gates_book_KMM.git")
        val screenWidth = ScreenPlatform().getScreenWidth()
        App(hapticFeedback = hapticFeedback, uri = uri, screenWidth = screenWidth)
    }
}