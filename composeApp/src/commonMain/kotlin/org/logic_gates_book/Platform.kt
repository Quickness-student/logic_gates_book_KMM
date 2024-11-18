package org.logic_gates_book

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