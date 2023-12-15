package com.celalkorucu.kotlinartbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.celalkorucu.kotlinartbook.databinding.RecyclerRowBinding

class ArtAdapter(val artList : ArrayList<Art>)  : RecyclerView.Adapter<ArtAdapter.ArtHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtHolder {

        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ArtHolder(binding)

    }


    override fun onBindViewHolder(holder: ArtHolder, position: Int) {


        holder.binding.nameText.text = artList.get(position).name
        holder.binding.artImage.setImageBitmap(artList.get(position).image)
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailsActivity::class.java)
            intent.putExtra("info", "old")
            intent.putExtra("id", artList[position].id)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {

        return artList.size
    }

    class ArtHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }


}