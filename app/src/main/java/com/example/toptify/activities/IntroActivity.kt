package com.example.toptify.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.toptify.databinding.ActivityIntroBinding
import com.spotify.sdk.android.auth.AccountsQueryParameters.CODE
import com.spotify.sdk.android.auth.AuthorizationClient
import com.spotify.sdk.android.auth.AuthorizationRequest
import com.spotify.sdk.android.auth.AuthorizationResponse
import com.spotify.sdk.android.auth.LoginActivity.REQUEST_CODE


class IntroActivity : AppCompatActivity() {
    lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val CLIENT_ID = "c6c23e3e2f604f9aa1780fe7504e73c6"
            val REQUEST_CODE = 1138
            val REDIRECT_URI = "com.example.toptify://callback"

            val builder =
                AuthorizationRequest.Builder(
                    CLIENT_ID,
                    AuthorizationResponse.Type.CODE,
                    REDIRECT_URI
                )

            builder.setScopes(arrayOf("user-top-read"))
            val request = builder.build()


            AuthorizationClient.openLoginActivity(this, REQUEST_CODE, request)


        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {

        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == REQUEST_CODE) {
            val response = AuthorizationClient.getResponse(resultCode, intent)
            when (response.type) {
                AuthorizationResponse.Type.CODE ->{
                    Log.i("THE CODE",response.code)
                    val newIntent = Intent(this,TopsActivity::class.java)
                    newIntent.putExtra("code",response.code)
                    startActivity(newIntent)
                    finish()
                }


                AuthorizationResponse.Type.ERROR -> {
                    Log.i("Something", "is wrong")
                }
                else -> {}
            }
        }else{
            Log.i("Hello",requestCode.toString())
        }
    }

}