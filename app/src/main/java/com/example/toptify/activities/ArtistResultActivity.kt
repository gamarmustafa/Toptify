package com.example.toptify.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toptify.adapters.ArtistAdapter
import com.example.toptify.adapters.TrackAdapter
import com.example.toptify.artists.Artists
import com.example.toptify.databinding.ArtistItemLayoutBinding
import com.example.toptify.databinding.ResultBinding
import com.example.toptify.tracks.Tracks

class ArtistResultActivity : AppCompatActivity() {
    lateinit var binding: ResultBinding
    private var adapter: ArtistAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ResultBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val bundle: Bundle? = intent.extras
        val title:String? = bundle!!.getString("title")
        val list = bundle.getSerializable("list") as Artists

        adapter = ArtistAdapter(list)
        binding.title.text=title
        binding.rv.adapter = adapter
        binding.rv.layoutManager = LinearLayoutManager(this)
    }
}