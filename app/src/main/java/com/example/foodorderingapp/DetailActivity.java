package com.example.foodorderingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodorderingapp.databinding.ActivityDetailBinding;
import com.example.foodorderingapp.databinding.ActivityMainBinding;

public class DetailActivity extends AppCompatActivity {
    ActivityDetailBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        /* plus and minus button functionality increase and decrease the quantity */

        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity =Integer.parseInt(binding.quantity.getText().toString());
                quantity++;
                binding.quantity.setText(""+quantity);
            }
        });
        binding.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int quantity = Integer.parseInt(binding.quantity.getText().toString());
                if(quantity>1)
                quantity--;
                else
                {}
                binding.quantity.setText(""+quantity);
            }
        });
        final DBHelper helper = new DBHelper(this);

        if(getIntent().getIntExtra("type" , 0) == 1) {
            final int image = getIntent().getIntExtra("image", 0);
            final String name = getIntent().getStringExtra("name");
            final int price = Integer.parseInt(getIntent().getStringExtra("price"));
            final String description = getIntent().getStringExtra("description");

            binding.detailImageView.setImageResource(image);
            binding.detailFoodName.setText(name);
            binding.detailPrice.setText(String.format("%d", price));
            binding.detailDescription.setText(description);



            binding.detailOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isInserted = helper.insertOrder(binding.detailUserName.getText().toString(),
                            binding.detailPhoneNumber.getText().toString(),
                            description,
                            name,
                            price,
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()));

                    if (isInserted)
                        Toast.makeText(DetailActivity.this, "Data is Inserted successfully ", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(DetailActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else {
            int id = getIntent().getIntExtra("id" , 0);
            Cursor cursor = helper.getOrderById(id);

            final  int image = cursor.getInt(4);
            binding.detailImageView.setImageResource(image);
            binding.detailFoodName.setText(cursor.getString(7));
            binding.detailPrice.setText(String.format("%d", cursor.getInt(3)));
            binding.detailDescription.setText(cursor.getString(6));
            binding.detailUserName.setText(cursor.getString(1));
            binding.detailPhoneNumber.setText(cursor.getString(2));
            binding.quantity.setText(String.format("%d", cursor.getInt(5)));
            binding.detailOrderButton.setText("Update Now");

            
            binding.detailOrderButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Boolean isUpdated = helper.updateOrder(binding.detailUserName.getText().toString() ,
                            binding.detailPhoneNumber.getText().toString(),
                            binding.detailDescription.getText().toString(),
                            binding.detailFoodName.getText().toString(),
                            Integer.parseInt(binding.detailPrice.getText().toString()),
                            image,
                            Integer.parseInt(binding.quantity.getText().toString()),
                            id);

                    if (isUpdated){
                        Toast.makeText(DetailActivity.this, "Update Successfully", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DetailActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}