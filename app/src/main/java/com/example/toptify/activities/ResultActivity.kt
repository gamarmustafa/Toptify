package com.example.toptify.activities

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toptify.R
import com.example.toptify.adapters.TrackAdapter
import com.example.toptify.databinding.ResultBinding
import com.example.toptify.tracks.Tracks

class ResultActivity : AppCompatActivity() {
    lateinit var binding: ResultBinding
    private var adapter: TrackAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle: Bundle? = intent.extras
        val title:String? = bundle!!.getString("title")
        val list = bundle!!.getSerializable("list") as Tracks

        adapter = TrackAdapter(list)
        binding.title.text=title
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }
}