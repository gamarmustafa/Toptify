package com.example.toptify.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.toptify.databinding.ActivityTopsBinding

class TopsActivity : AppCompatActivity() {
    lateinit var binding: ActivityTopsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTopsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val code: String = bundle!!.getString("code").toString()
        Log.i("code", code)

    }

}