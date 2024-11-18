package org.logic_gates_book

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