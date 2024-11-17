package org.logic_gates_book.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = tertiary,
    error = error,
    background = background
)

private val DarkColorScheme = darkColorScheme(
    primary = Primary,
    secondary = Secondary,
    tertiary = tertiary,
    error = error,
    background = background
)

@Composable
fun MaterialThemeApp(
    content: @Composable () -> Unit
) {
    val isSystemDark = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (isSystemDark) DarkColorScheme else LightColorScheme
    ) {
        content()
    }
}