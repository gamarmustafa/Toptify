package com.example.toptify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toptify.databinding.TrackItemLayoutBinding
import com.example.toptify.tracks.Tracks
import com.squareup.picasso.Picasso

class TrackAdapter(private val list:Tracks) :RecyclerView.Adapter<TrackAdapter.MyViewHolder>(){
    class MyViewHolder(val binding:TrackItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(TrackItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list.items[position]
        holder.binding.number.text = (position+1).toString()
        Picasso.get().load(item.album.images[0].url).into(holder.binding.image)
        holder.binding.trackName.text = item.name
        holder.binding.trackArtist.text = item.artists[0].name
    }

    override fun getItemCount(): Int {
        return list.items.size
    }
}