package com.theecoder.sajobsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_SAJobSearch)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.button).setOnClickListener{
            var intent: Intent = Intent(this, settings::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button2).setOnClickListener{
            var intent: Intent = Intent(this, user::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.button3).setOnClickListener{
            var intent: Intent = Intent(this, bookmark::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var menuInflater : MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.bookmark_1 ->{
                var intent: Intent = Intent(this, bookmark::class.java)
                startActivity(intent)
            }

            R.id.user_1 ->{
                var intent: Intent = Intent(this, user::class.java)
                startActivity(intent)
            }
            R.id.settings_1 ->{
                var intent: Intent = Intent(this, settings::class.java)
                startActivity(intent)
            }
        }

        return true
    }
}