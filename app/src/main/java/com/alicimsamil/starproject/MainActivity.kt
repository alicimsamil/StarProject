package com.alicimsamil.starproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alicimsamil.starproject.skywebview.SkyWebView

class MainActivity : AppCompatActivity() {

    private lateinit var skyWebView: SkyWebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the SkyWebView view in the layout by its ID
        skyWebView = findViewById(R.id.skyWebView)

        // Attach the SkyWebView to the lifecycle of the activity
        lifecycle.addObserver(skyWebView)
    }
}