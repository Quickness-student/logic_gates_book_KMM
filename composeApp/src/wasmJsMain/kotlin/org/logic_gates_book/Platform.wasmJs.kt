package org.logic_gates_book

import androidx.compose.runtime.Composable
import kotlinx.browser.window

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual class HapticFeedback {
    actual fun vibrate(durationMillis: Long) {
        // No-op en Wasm
    }
}

actual class Uri (private val url: String){
    actual fun navigate() {
        window.location.href = url
    }
}

actual class ScreenPlatform {
    @Composable
    actual fun getScreenWidth(): Float {
        return window.innerWidth.toFloat()
    }
}