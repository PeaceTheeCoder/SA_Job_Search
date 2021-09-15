package com.theecoder.sajobsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theecoder.sajobsearch.adapters.search_adapter

class SearchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_search)

        initSearch()

        findViewById<ImageButton>(R.id.back_btn).setOnClickListener{
            super.onBackPressed()
        }
    }

    private fun initSearch(){
        val search_recyclerview = findViewById<RecyclerView>(R.id.search_recyclerview)

        search_recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        search_recyclerview.adapter = search_adapter(this)

    }
}