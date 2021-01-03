package com.example.foodorderingapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.DetailActivity;
import com.example.foodorderingapp.Models.MainModels;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class MainAdapters extends RecyclerView.Adapter<MainAdapters.Viewholder>{
    ArrayList<MainModels> modelsArrayList;
    Context context;

    public MainAdapters(ArrayList<MainModels> modelsArrayList, Context context) {
        this.modelsArrayList = modelsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.main_layout , parent , false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final MainModels mainModels = modelsArrayList.get(position);
        holder.image.setImageResource(mainModels.getFoodImage());
        holder.name.setText(mainModels.getFoodName());
        holder.price.setText(mainModels.getFoodPrice());
        holder.description.setText(mainModels.getFoodDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("image" , mainModels.getFoodImage());
                intent.putExtra("name" , mainModels.getFoodName());
                intent.putExtra("price" , mainModels.getFoodPrice());
                intent.putExtra("description" , mainModels.getFoodDescription());
                intent.putExtra("type" , 1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelsArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name,price,description;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.foodImageView);
            name = itemView.findViewById(R.id.foodNameTextView);
            price = itemView.findViewById(R.id.foodPriceTextView);
            description = itemView.findViewById(R.id.foodDescriptionTextView);
        }
    }
}
