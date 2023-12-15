package com.celalkorucu.kotlinlandmarkbook

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView

import com.celalkorucu.kotlinlandmarkbook.databinding.RecyclerRowBinding

class LandmarkAdapter( var arrayList: ArrayList<Landmark>) : RecyclerView.Adapter<LandmarkAdapter.LandmarkHolder>() {

    class LandmarkHolder(val binding : RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LandmarkHolder {
        val binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context) , parent,false)
        return LandmarkHolder(binding)

    }

    override fun onBindViewHolder(holder: LandmarkHolder, position: Int) {
        holder.binding.nameText.text = arrayList.get(position).name
        holder.itemView.setOnClickListener {
            val  intent = Intent(holder.itemView.context , DetailsActivity::class.java)
            /*
            intent.putExtra("name" , arrayList.get(position).name)
            intent.putExtra("country" , arrayList.get(position).country)
            intent.putExtra("image" , arrayList.get(position).image)

             */
            intent.putExtra("landmark" , arrayList.get(position))
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {

        return arrayList.size
    }
}