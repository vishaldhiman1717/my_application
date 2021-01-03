package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.foodorderingapp.Adapters.OrderAdapters;
import com.example.foodorderingapp.Models.OrderModels;
import com.example.foodorderingapp.databinding.ActivityOrderBinding;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    ActivityOrderBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DBHelper helper = new DBHelper(this);
        ArrayList<OrderModels> arrayList = helper.getOrders();


        OrderAdapters orderAdapters = new OrderAdapters(arrayList , this);
        binding.orderRecyclerView.setAdapter(orderAdapters);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.orderRecyclerView.setLayoutManager(linearLayoutManager);

    }
}