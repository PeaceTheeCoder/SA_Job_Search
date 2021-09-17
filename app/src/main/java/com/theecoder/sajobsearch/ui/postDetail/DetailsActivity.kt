package com.theecoder.sajobsearch.ui.postDetail

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.theecoder.arkwork.data.api.PostDBClient
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.data.vo.PostDetails
import com.theecoder.arkwork.ui.postDetail.PostDetailRepository
import com.theecoder.arkwork.ui.postDetail.PostDetailViewModel
import com.theecoder.sajobsearch.R

class DetailsActivity : AppCompatActivity() {
    private lateinit var viewModel: PostDetailViewModel
    private lateinit var postRepository: PostDetailRepository
    private lateinit var Url: String

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
        openUrl()

        val postID: Int = intent.getIntExtra("postID",1)
        val apiService: PostDBInterface = PostDBClient.getClient()
        postRepository = PostDetailRepository(apiService)
        viewModel = getViewModel(postID)
        viewModel.postDetails.observe(this, Observer {
            bindUI(it)
        })


        viewModel.networkState.observe(this, Observer {

            /*findViewById<RelativeLayout>(R.id.progressbar_layout).visibility =
                if(it == NetworkState.LOADING) View.VISIBLE else View.GONE

            findViewById<RelativeLayout>(R.id.page).visibility = when(it){
                NetworkState.LOADED -> View.VISIBLE
                else -> View.GONE
            }
            findViewById<RelativeLayout>(R.id.error_layout).visibility = when(it){
                NetworkState.ERROR -> View.VISIBLE
                else -> View.GONE
            }*/ Log.d("adView", "Ad showed fullscreen content.")
        })
    }
    private fun bindUI(it : PostDetails){
        findViewById<TextView>(R.id.job_title).text = it.title
        findViewById<TextView>(R.id.job_date).text = it.created
        findViewById<TextView>(R.id.job_location).text = it.location
        findViewById<TextView>(R.id.job_salary).text = it.salary
        findViewById<TextView>(R.id.job_closing_date).text = it.closing
        findViewById<TextView>(R.id.job_description).text = it.body
        Url = it.url



    }
    private fun getViewModel(postID: Int): PostDetailViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>):T{
                @Suppress("UNCHECKED_CAST")
                return PostDetailViewModel(postRepository, postID) as T
            }
        })[PostDetailViewModel::class.java]
    }
    private fun openUrl(){
        findViewById<Button>(R.id.Apply_button).setOnClickListener{
            if(Url.startsWith("https://") or Url.startsWith("http://")){
                val intent: Intent = Intent(Intent.ACTION_VIEW, Uri.parse(Url))
                startActivity(intent)
            }
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