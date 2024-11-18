package org.logic_gates_book

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val systemUiController = rememberSystemUiController()
            val hapticFeedback = HapticFeedback(this)
            val uri = Uri(
                url = "https://github.com/Quickness-student/logic_gates_book_KMM.git",
                context = this
            )
            LaunchedEffect(Unit) {
                systemUiController.isSystemBarsVisible = false
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent, // Hace que las barras del sistema sean transparentes
                    darkIcons = Color.White.luminance() > 0.5f // Determina si los iconos deben ser oscuros o claros
                )
            }
            App(hapticFeedback = hapticFeedback, uri = uri)
        }
    }
}