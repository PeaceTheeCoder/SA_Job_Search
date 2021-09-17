package com.theecoder.sajobsearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theecoder.arkwork.data.api.PostDBClient
import com.theecoder.arkwork.data.api.PostDBInterface
import com.theecoder.arkwork.data.api.repository.NetworkState
import com.theecoder.arkwork.ui.posts.MainActivityViewModel
import com.theecoder.arkwork.ui.posts.PostPagedListRepository
import com.theecoder.sajobsearch.R
import com.theecoder.sajobsearch.ui.adapters.postsAdapter

class SearchActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel
    private lateinit var postRepository: PostPagedListRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_search)

        initSearch(null)

        findViewById<ImageButton>(R.id.back_btn).setOnClickListener{
            super.onBackPressed()
        }
    }

    private fun getViewModel(repo: PostPagedListRepository): MainActivityViewModel {
        return ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>):T{
                @Suppress("UNCHECKED_CAST")
                return MainActivityViewModel(repo) as T
            }
        })[MainActivityViewModel::class.java]
    }
    private fun initSearch(Search : String?){
        val apiService: PostDBInterface = PostDBClient.getClient()
        postRepository = PostPagedListRepository(apiService,null,null,"-created",Search)
        viewModel = getViewModel(postRepository)

        val postAdapter = postsAdapter(this)
        val layourManager = LinearLayoutManager(this)
        val posts_recyclerview = findViewById<RecyclerView>(R.id.search_recyclerview)


        posts_recyclerview.layoutManager = layourManager
        posts_recyclerview.hasFixedSize()
        posts_recyclerview.adapter = postAdapter

        viewModel.postPagedList.observe(this, Observer {
            postAdapter.submitList(it)
            Log.e("DATA_CHAMGE", it.toString())
        })
        viewModel.networkState.observe(this, Observer {
            /*findViewById<ProgressBar>(R.id.progressBar).visibility =
                if(it == NetworkState.LOADING) View.VISIBLE else View.GONE

            findViewById<TextView>(R.id.errorText).visibility = when(it){
                NetworkState.ERROR -> View.VISIBLE
                else -> View.GONE
            }

            if(!viewModel.listEmpty()){
                postAdapter.setNetworkState(it)
            }*/Log.d("adView", "Ad showed fullscreen content.")
        })


    }
}