package com.example.toptify.activities

import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.toptify.databinding.ActivityTopsBinding
import com.example.toptify.utils.API
import com.example.toptify.utils.Token
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import retrofit.*

const val URL = "https://accounts.spotify.com"
const val CLIENT_ID = "c6c23e3e2f604f9aa1780fe7504e73c6"
const val CLIENT_SECRET = "8400b843e13a461d84c7b0b2855f4522"
const val REDIRECT_URI = "com.example.toptify://callback"

class TopsActivity : BaseActivity() {
    lateinit var binding: ActivityTopsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTopsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val code: String = bundle!!.getString("code").toString()
         getToken(code)


        binding.icLogout.setOnClickListener{
            openWebView(binding.webview)
        }

    }

    private fun openWebView(webView: WebView){
        val url= "https://accounts.spotify.com/en/logout"
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url)
        Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
        startActivity((Intent(this,IntroActivity::class.java)))
        finish()
    }




    private fun getToken(code: String) {
        val base64String =
            "Basic " + get64BaseString("c6c23e3e2f604f9aa1780fe7504e73c6:8400b843e13a461d84c7b0b2855f4522")
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service: API = retrofit.create(API::class.java)
        val listCall: Call<Token> = service.requestToken(
            base64String, "application/x-www-form-urlencoded", "authorization_code",
            code, REDIRECT_URI)

        listCall.enqueue(object : Callback<Token> {
            override fun onResponse(response: Response<Token>?, retrofit: Retrofit?) {

                if (response?.body() != null) {

                    binding.btnTopTracks.setOnClickListener {
                        showProgressDialog()
                        val intent = Intent(this@TopsActivity, TopTracksActivity::class.java)
                        intent.putExtra("token",response.body().access_token)
                        hideProgressDialog()
                        startActivity(intent)


                    }
                    binding.btnTopArtists.setOnClickListener {
                        showProgressDialog()
                        val intent = Intent(this@TopsActivity, TopArtistsActivity::class.java)
                        intent.putExtra("token",response.body().access_token)
                        hideProgressDialog()
                        startActivity(intent)

                    }
                    binding.btnTopGenres.setOnClickListener {
                        showProgressDialog()
                        val intent = Intent(this@TopsActivity, TopGenresActivity::class.java)
                        intent.putExtra("token",response.body().access_token)
                        hideProgressDialog()
                        startActivity(intent)
                    }
                }
                if (response?.body() == null) {
                    Log.i("Response!", "null response body /getToken")
                }
            }

            override fun onFailure(t: Throwable?) {
                Log.e("Error", t!!.message.toString())
            }

        })

    }

    private fun get64BaseString(value: String): String {
        return Base64.encodeToString(value.toByteArray(), Base64.NO_WRAP)
    }

}