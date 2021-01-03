package com.example.foodorderingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.foodorderingapp.Models.OrderModels;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    final static String DBName = "mydatabase.db";
    final static int DBVersion = 1;
    public DBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table orders" +
                        "(id integer primary key autoincrement," +
                        "name text," +
                        "phone text," +
                        "price int,"+
                        "image int,"+
                        "quantity int,"+
                        "description text," +
                        "foodname text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists orders");
        onCreate(db);
    }
    public Boolean insertOrder(String name , String phone , String description , String foodname , int price , int image ,int quantity) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
        0 = id
        1 = name
        2 = phone
        3 = price
        4 = image
        5 = quantity
        6 = des
        6 = foodName
        7 = quantity
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        long id = database.insert("orders", null, values);

        if (id > 0)
            return true;
        else
            return false;
    }

    public ArrayList<OrderModels> getOrders(){
        ArrayList<OrderModels> orders = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select id,foodname,image,price from  orders" , null);
        if (cursor.moveToFirst())
        {
            while (cursor.moveToNext()){
                OrderModels models = new OrderModels(2,null,null,null);
                models.setOrderFoodNumber(cursor.getInt(0)+"");
                models.setOrderFoodName(cursor.getString(1));
                models.setOrderFoodImage(cursor.getInt(2));
                models.setOrderFoodPrice(cursor.getInt(3)+"");
                orders.add(models);
            }
        }
        cursor.close();
        database.close();
        return orders;
    }
    public Cursor getOrderById(int id){
            SQLiteDatabase database = this.getWritableDatabase();
            Cursor cursor = database.rawQuery("Select * from  orders where id = "+ id , null);

            if(cursor != null) {
                cursor.moveToFirst();
            }

            return cursor;
        }

    public Boolean updateOrder(String name , String phone , String description , String foodname , int price , int image ,int quantity , int id) {
        SQLiteDatabase database = getReadableDatabase();
        ContentValues values = new ContentValues();

        /*
        0 = id
        1 = name
        2 = phone
        3 = price
        4 = image
        5 = quantity
        6 = des
        7 = quantity
         */
        values.put("name", name);
        values.put("phone", phone);
        values.put("price", price);
        values.put("image", image);
        values.put("description", description);
        values.put("foodname", foodname);
        values.put("quantity", quantity);
        long row = database.update("orders" , values , "id="+id , null);

        if (row > 0)
            return true;
        else
            return false;
    }

    public int deleteOrder(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return  sqLiteDatabase.delete("orders" , "id="+id , null);

    }
    }
