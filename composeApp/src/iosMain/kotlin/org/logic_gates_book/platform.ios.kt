package org.logic_gates_book


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