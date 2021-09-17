


package com.theecoder.sajobsearch.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.android.gms.ads.*
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.theecoder.sajobsearch.R

class MainActivity : AppCompatActivity() {
    lateinit var mAdView : AdView
    private var mInterstitialAd: InterstitialAd? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setTheme(R.style.Main_Theme)
        setContentView(R.layout.activity_main)

        loadAdBanner()
        loadAdInterstatial()

        findViewById<Button>(R.id.search_button).setOnClickListener {
            showInterAd()
        }

    }

    private fun showInterAd() {
        if(mInterstitialAd != null){
            mInterstitialAd?.fullScreenContentCallback = object: FullScreenContentCallback(){
                override fun onAdDismissedFullScreenContent() {
                    Log.d("adView", "Ad was dismissed.")
                    val i = Intent(this@MainActivity, SearchActivity::class.java)
                    startActivity(i)
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError?) {
                    Log.d("adView", "Ad failed to show.")

                }

                override fun onAdShowedFullScreenContent() {
                    Log.d("adView", "Ad showed fullscreen content.")
                    mInterstitialAd = null
                }

            }

            mInterstitialAd?.show(this@MainActivity)
        }else{
            val i = Intent(this@MainActivity, SearchActivity::class.java)
            startActivity(i)
        }
    }

    private fun loadAdBanner()
    {
        MobileAds.initialize(this) {}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }
    private fun loadAdInterstatial(){
        var adRequest = AdRequest.Builder().build()

        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, object : InterstitialAdLoadCallback() {
            override fun onAdFailedToLoad(adError: LoadAdError) {
                mInterstitialAd = null
            }

            override fun onAdLoaded(interstitialAd: InterstitialAd) {
                mInterstitialAd = interstitialAd
            }
        })
    }

}