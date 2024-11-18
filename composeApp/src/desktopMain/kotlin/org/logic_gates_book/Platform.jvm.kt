package org.logic_gates_book

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalWindowInfo
import java.awt.Desktop
import java.net.URI

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
}

actual fun getPlatform(): Platform = JVMPlatform()

actual class HapticFeedback {
    actual fun vibrate(durationMillis: Long) {
        // No-op en JVM
    }
}

actual class Uri (private val url: String){
    actual fun navigate() {
        if (Desktop.isDesktopSupported()) {
            val desktop = Desktop.getDesktop()
            try {
                // Usamos URI para abrir la URL en el navegador
                desktop.browse(URI(url))
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

actual class ScreenPlatform {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    actual fun getScreenWidth(): Float {
        val window = LocalWindowInfo.current
        return window.containerSize.width.toFloat()
    }
}