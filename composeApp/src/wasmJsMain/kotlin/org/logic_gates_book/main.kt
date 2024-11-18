package org.logic_gates_book

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(viewportContainer = document.body!!) {
        val hapticFeedback = HapticFeedback()
        val uri = Uri("https://github.com/Quickness-student/logic_gates_book_KMM.git")
        val screenWidth = ScreenPlatform().getScreenWidth()
        App(hapticFeedback = hapticFeedback, uri = uri, screenWidth = screenWidth)
    }
}