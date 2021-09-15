package com.theecoder.sajobsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_main)

        var session = intent.getStringExtra("login")
        if(session == "yes"){
            findViewById<Button>(R.id.search_button).setOnClickListener{
                val i = Intent(this, SearchActivity::class.java)
                startActivity(i)
            }
        }else{
            val i = Intent(this, LoginActivity::class.java)
            startActivity(i)
            finish()
        }
    }


}