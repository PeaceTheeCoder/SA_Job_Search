package com.theecoder.sajobsearch

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_details)

        findViewById<ImageButton>(R.id.back_btn).setOnClickListener{
            super.onBackPressed()
        }
        findViewById<ImageButton>(R.id.share_button).setOnClickListener{
            shareText()
        }
    }

    private fun shareText()
    {
        val title = findViewById<TextView>(R.id.job_title).text
        val company = findViewById<TextView>(R.id.job_company_name).text
        val salary = findViewById<TextView>(R.id.job_salary).text
        val closing = findViewById<TextView>(R.id.job_closing_date).text

        val message:  String = "$title".toUpperCase()+"\r\n"+
                "Company name -> ${company}\r\n"+
                "Salary -> ${salary}\r\n"+
                "Closing date -> $closing"

        val intent = Intent()
        intent.action = Intent.ACTION_SEND
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent,"Share to :"))

    }
}