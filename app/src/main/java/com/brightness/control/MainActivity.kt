package com.brightness.control

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.MotionEvent
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    
    private lateinit var brightnessSlider: Slider
    private lateinit var minBrightnessButton: android.widget.TextView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Minimal pencere ayarları
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        
        window.setGravity(Gravity.TOP)
        window.addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL)
        window.addFlags(WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH)
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        setFinishOnTouchOutside(true)
        
        brightnessSlider = findViewById(R.id.brightnessSlider)
        minBrightnessButton = findViewById(R.id.minBrightnessButton)
        
        // Slider değişikliğini dinle
        brightnessSlider.addOnChangeListener { _, value, fromUser ->
            if (fromUser) {
                val brightnessValue = value.toInt()
                BrightnessManager.setBrightness(this, brightnessValue)
            }
        }
        
        // Minimum parlaklık butonu
        minBrightnessButton.setOnClickListener {
            brightnessSlider.value = 3f
            BrightnessManager.setBrightness(this, 3)
        }
    }
    
    override fun onResume() {
        super.onResume()
        
        // Uygulamayı her açtığında kaydedilen parlaklığı restore et
        val prefs = getSharedPreferences("brightness_prefs", Context.MODE_PRIVATE)
        val savedBrightness = prefs.getInt("saved_brightness", 200)
        BrightnessManager.setBrightness(this, savedBrightness)
    }
    
    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_OUTSIDE) {
            finish()
            return true
        }
        return super.onTouchEvent(event)
    }
}
