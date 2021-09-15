package com.theecoder.sajobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_details)

        findViewById<ImageButton>(R.id.back_btn).setOnClickListener{
            super.onBackPressed()
        }
    }
}