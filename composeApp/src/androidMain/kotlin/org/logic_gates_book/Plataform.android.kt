package org.logic_gates_book

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator

class AndroidPlatform : Platform {
    override val name: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()

actual class HapticFeedback(context: Context) {

    private val vibrator = context.getSystemService(Vibrator::class.java)

    actual fun vibrate(durationMillis: Long) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(VibrationEffect.createOneShot(durationMillis, VibrationEffect.DEFAULT_AMPLITUDE))
        }
    }
}

actual class Uri (url: String, private val context: Context) {
    private val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    actual fun navigate() {
        context.startActivity(intent)
    }
}