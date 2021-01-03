package com.example.foodorderingapp.Models;

public class OrderModels {
    int orderFoodImage;
    String orderFoodName , orderFoodNumber , orderFoodPrice;

    public OrderModels(int orderFoodImage, String orderFoodName, String orderFoodNumber, String orderFoodPrice) {
        this.orderFoodImage = orderFoodImage;
        this.orderFoodName = orderFoodName;
        this.orderFoodNumber = orderFoodNumber;
        this.orderFoodPrice = orderFoodPrice;
    }

    public int getOrderFoodImage() {
        return orderFoodImage;
    }

    public void setOrderFoodImage(int orderFoodImage) {
        this.orderFoodImage = orderFoodImage;
    }

    public String getOrderFoodName() {
        return orderFoodName;
    }

    public void setOrderFoodName(String orderFoodName) {
        this.orderFoodName = orderFoodName;
    }

    public String getOrderFoodNumber() {
        return orderFoodNumber;
    }

    public void setOrderFoodNumber(String orderFoodNumber) {
        this.orderFoodNumber = orderFoodNumber;
    }

    public String getOrderFoodPrice() {
        return orderFoodPrice;
    }

    public void setOrderFoodPrice(String orderFoodPrice) {
        this.orderFoodPrice = orderFoodPrice;
    }
}
