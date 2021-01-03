package com.example.foodorderingapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorderingapp.DBHelper;
import com.example.foodorderingapp.DetailActivity;
import com.example.foodorderingapp.Models.OrderModels;
import com.example.foodorderingapp.R;

import java.util.ArrayList;

public class OrderAdapters extends RecyclerView.Adapter<OrderAdapters.Viewholder>{

    ArrayList<OrderModels> arrayList;
    Context context;

    public OrderAdapters(ArrayList<OrderModels> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_order , parent ,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        final OrderModels orderModels = arrayList.get(position);
        holder.orderImage.setImageResource(orderModels.getOrderFoodImage());
        holder.orderName.setText(orderModels.getOrderFoodName());
        holder.orderNumber.setText(orderModels.getOrderFoodNumber());
        holder.orderPrice.setText(orderModels.getOrderFoodPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , DetailActivity.class);
                intent.putExtra("id" ,Integer.parseInt(orderModels.getOrderFoodNumber()));
                intent.putExtra("type" , 2);
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete item")
                        .setIcon(R.drawable.warning)
                        .setMessage("You want to delete this item")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                DBHelper dbHelper = new DBHelper(context);
                                if(dbHelper.deleteOrder(orderModels.getOrderFoodNumber()) > 0){
                                    Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        ImageView orderImage;
        TextView orderName , orderNumber , orderPrice;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            orderImage = itemView.findViewById(R.id.orderFoodImage);
            orderName = itemView.findViewById(R.id.orderFoodName);
            orderPrice= itemView.findViewById(R.id.orderFoodPrice);
            orderNumber = itemView.findViewById(R.id.orderNumber);
        }
    }
}
