package org.logic_gates_book

import androidx.compose.runtime.Composable


class IOSPlatform: Platform {
    override val name: String = "IOS"
}

actual fun getPlatform(): Platform = IOSPlatform()

actual class HapticFeedback {
    actual fun vibrate(durationMillis: Long) {
        // No-op on iOS
    }
}

actual class Uri (private val url: String){
    actual fun navigate() {
        // No-op on iOS
    }
}

actual class ScreenPlatform {
    @Composable
    actual fun getScreenWidth(): Float {
        // No-op on iOS
        return 1f
    }
}