package com.theecoder.sajobsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_login)

        findViewById<Button>(R.id.signin_button).setOnClickListener{
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("login","yes")
            startActivity(i)
            finish()
        }

        findViewById<TextView>(R.id.signup_text).setOnClickListener{
            val ii = Intent(this, SignupActivity::class.java)
            startActivity(ii)
            finish()
        }
    }
}