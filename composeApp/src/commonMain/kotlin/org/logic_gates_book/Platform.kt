package org.logic_gates_book

import androidx.compose.runtime.Composable

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform

expect class HapticFeedback {
    fun vibrate(durationMillis: Long)
}

expect class Uri{
    fun navigate()
}

expect class ScreenPlatform {
    @Composable
    fun getScreenWidth(): Float
}