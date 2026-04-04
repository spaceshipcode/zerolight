package com.brightness.control

import android.content.Context
import java.io.DataOutputStream

object BrightnessManager {
    
    private const val PREFS_NAME = "brightness_prefs"
    private const val BRIGHTNESS_KEY = "saved_brightness"
    
    /**
     * Parlaklık değerini sistem dosyasına yaz ve SharedPreferences'e kaydet
     */
    fun setBrightness(context: Context, value: Int) {
        // SharedPreferences'e kaydet (ekran açıldığında restore için)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putInt(BRIGHTNESS_KEY, value)
            .apply()
        
        // Root ile sistem dosyasını güncelle (hızlı, non-blocking)
        executeRootCommand("echo $value > /sys/class/leds/lcd-backlight/brightness")
    }
    
    /**
     * Root komutu çalıştır
     */
    private fun executeRootCommand(command: String) {
        Thread {
            try {
                val process = Runtime.getRuntime().exec("su")
                val os = DataOutputStream(process.outputStream)
                
                os.writeBytes("$command\n")
                os.writeBytes("exit\n")
                os.flush()
                
                process.waitFor()
                os.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}
