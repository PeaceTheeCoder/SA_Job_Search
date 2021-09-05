package com.theecoder.sajobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SAJobSearch)
        setContentView(R.layout.activity_settings)
    }
}