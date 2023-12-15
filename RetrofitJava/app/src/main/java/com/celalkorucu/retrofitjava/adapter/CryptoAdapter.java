package com.celalkorucu.retrofitjava.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.celalkorucu.retrofitjava.databinding.CryptoRecyclerRowBinding;
import com.celalkorucu.retrofitjava.model.CryptoModel;

import java.util.ArrayList;

public class CryptoAdapter extends RecyclerView.Adapter<CryptoAdapter.CryptoHolder> {


   ArrayList<CryptoModel> cryptoModelArrayList ;
   String colors [] = {"#92dfff","#00703c","#01796f","#177245","#8c92ac","#cd7f32","#009966","#009900","#dcdcdc","#5b264f"};
    public CryptoAdapter(ArrayList<CryptoModel>  cryptoModelArrayList){
        this.cryptoModelArrayList = cryptoModelArrayList ;


    }


    @NonNull
    @Override
    public CryptoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CryptoRecyclerRowBinding binding = CryptoRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()) , parent , false);

        return new CryptoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CryptoHolder holder, int position) {
        holder.binding.cryptoName.setText(cryptoModelArrayList.get(position).getCurrency());
        holder.binding.cryptoPrice.setText(cryptoModelArrayList.get(position).getPrice()+" $");
        holder.bind(position);
    }



    @Override
    public int getItemCount() {
        return cryptoModelArrayList.size();
    }

    public class CryptoHolder extends RecyclerView.ViewHolder{

        CryptoRecyclerRowBinding binding ;

        public CryptoHolder(CryptoRecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding=binding ;
        }
        public void bind(int position){
            binding.getRoot().setBackgroundColor(Color.parseColor(colors[position%10]));
        }
    }
}
