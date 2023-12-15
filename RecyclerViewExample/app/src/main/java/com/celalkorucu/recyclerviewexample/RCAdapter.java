package com.celalkorucu.recyclerviewexample;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.celalkorucu.recyclerviewexample.databinding.RecyclerRowBinding;

import java.util.ArrayList;

public class RCAdapter extends RecyclerView.Adapter<RCAdapter.RCHolder> {

    ArrayList<String> list ;
    public RCAdapter(ArrayList<String> list){
        this.list=list;
    }

    @NonNull
    @Override
    public RCAdapter.RCHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerRowBinding binding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);

        return new RCHolder(binding);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull RCAdapter.RCHolder holder, int position) {

       holder.binding.recyclerViewText.setText(list.get(position));
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               Intent intent = new Intent(holder.itemView.getContext() , DetailsActivity.class);
               intent.putExtra("data",list.get(position));
               holder.itemView.getContext().startActivity(intent);
               System.out.println(list.get(position));
           }
       });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RCHolder extends RecyclerView.ViewHolder{
        private RecyclerRowBinding binding ;

        public RCHolder(RecyclerRowBinding binding){
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
