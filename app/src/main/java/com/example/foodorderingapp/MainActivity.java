package com.example.foodorderingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.foodorderingapp.Adapters.MainAdapters;
import com.example.foodorderingapp.Models.MainModels;
import com.example.foodorderingapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<MainModels> arrayList = new ArrayList<>();
        arrayList.add(new MainModels(R.mipmap.chilli_burger, "chilli Burger" , "5" , "Our Special chili Burger"));
        arrayList.add(new MainModels(R.mipmap.chicken_burger, "Chicken Burger" , "15" , "Our Special Chicken Burger"));
        arrayList.add(new MainModels(R.mipmap.combo, "combo Burger" , "25" , "Our Special combo"));
        arrayList.add(new MainModels(R.mipmap.sandwich, "sandwich" , "35" , "Our Special sandwich"));
        arrayList.add(new MainModels(R.mipmap.tikka, "tikka" , "45" , "Our Special tikka"));
        arrayList.add(new MainModels(R.mipmap.chilli_burger, "chilli Burger" , "5" , "Our Special chili Burger"));
        arrayList.add(new MainModels(R.mipmap.chicken_burger, "Chicken Burger" , "15" , "Our Special Chicken Burger"));
        arrayList.add(new MainModels(R.mipmap.combo, "combo Burger" , "25" , "Our Special combo"));
        arrayList.add(new MainModels(R.mipmap.sandwich, "sandwich" , "35" , "Our Special sandwich"));
        arrayList.add(new MainModels(R.mipmap.tikka, "tikka" , "45" , "Our Special tikka"));


        MainAdapters mainAdapters = new MainAdapters(arrayList , this);
        binding.recyclerview.setAdapter(mainAdapters);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerview.setLayoutManager(linearLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.orders:
                startActivity(new Intent(MainActivity.this , OrderActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Exit")
                .setIcon(R.drawable.warning)
                .setMessage("Are you sure want to exit")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
    }
}