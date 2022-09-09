package com.example.toptify.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.toptify.artists.Artists
import com.example.toptify.databinding.ArtistItemLayoutBinding
import com.squareup.picasso.Picasso

class ArtistAdapter(private val list: Artists) :RecyclerView.Adapter<ArtistAdapter.MyViewHolder>(){
    class MyViewHolder(val binding:ArtistItemLayoutBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ArtistItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list.items[position]
        holder.binding.number.text = (position+1).toString()
        Picasso.get().load(item.images[0].url).into(holder.binding.image)
        holder.binding.artistName.text = item.name

    }

    override fun getItemCount(): Int {
        return list.items.size
    }
}