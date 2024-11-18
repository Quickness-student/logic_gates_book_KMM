package org.logic_gates_book.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = tertiary,
    background = background,
    error = error
)

@Composable
fun MaterialThemeApp(content: @Composable () -> Unit) =
    MaterialTheme(colorScheme = DarkColorScheme, content = content)