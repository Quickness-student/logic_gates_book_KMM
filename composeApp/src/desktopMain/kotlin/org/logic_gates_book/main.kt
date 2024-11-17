package org.logic_gates_book

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "logic_gates_book_AIDW",
    ) {
        App()
    }
}