package com.example.toptify.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.example.toptify.R
import com.example.toptify.databinding.ActivityTopTracksBinding
import com.example.toptify.tracks.Tracks
import com.example.toptify.utils.API
import com.example.toptify.utils.Token
import com.spotify.sdk.android.auth.AccountsQueryParameters.CLIENT_ID
import retrofit.*

const val API_URL = "https://api.spotify.com"
const val FOUR_WEEKS = "short_term"
const val SIX_MONTHS = "medium_term"
const val ALL_TIME = "long_term"
class TopTracksActivity : AppCompatActivity() {
    lateinit var binding : ActivityTopTracksBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTopTracksBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val token: String = bundle!!.getString("token").toString()

        val retrofit: Retrofit = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: API = retrofit.create(API::class.java)

        binding.btn4Weeks.setOnClickListener {
            val listCall:Call<Tracks> =service.getTracks(": Bearer "+token,20,0, FOUR_WEEKS)
            listCall.enqueue(object : Callback<Tracks>{
                override fun onResponse(response: Response<Tracks>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        Log.i("Tracks Body",response.body().items.toString())
                        val intent =Intent(this@TopTracksActivity,ResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Tracks\n(last 4 weeks)")
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }
        binding.btn6Month.setOnClickListener {
            val listCall:Call<Tracks> =service.getTracks(": Bearer "+token,20,0, SIX_MONTHS)
            listCall.enqueue(object : Callback<Tracks>{
                override fun onResponse(response: Response<Tracks>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        Log.i("Tracks Body",response.body().items.toString())
                        val intent =Intent(this@TopTracksActivity,ResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Tracks\n(last 6 months)")
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }
        binding.btnAllTime.setOnClickListener {
        val listCall:Call<Tracks> =service.getTracks(": Bearer "+token,20,0, ALL_TIME)
            listCall.enqueue(object : Callback<Tracks>{
                override fun onResponse(response: Response<Tracks>?, retrofit: Retrofit?) {

                    if (response?.body() != null) {
                        Log.i("Tracks Body",response.body().items.toString())
                        val intent =Intent(this@TopTracksActivity,ResultActivity::class.java)
                        intent.putExtra("list",response.body())
                        intent.putExtra("title","Top Tracks\n(all time)")
                        startActivity(intent)
                    }


                    if (response?.body() == null) {
                        Log.i("Response!", "null response body /4weeks")
                    }
                }

                override fun onFailure(t: Throwable?) {
                    Log.e("Error", t!!.message.toString())
                }

            })
        }

    }






}