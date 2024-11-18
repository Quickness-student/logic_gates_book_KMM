package org.logic_gates_book.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import org.logic_gates_book.HapticFeedback
import org.logic_gates_book.Uri
import org.logic_gates_book.ui.navigation.NavControllerStart

@Composable
fun Main(hapticFeedback: HapticFeedback, uri: Uri) {
    val navController = rememberNavController()
    NavControllerStart(navController, hapticFeedback, uri)
}