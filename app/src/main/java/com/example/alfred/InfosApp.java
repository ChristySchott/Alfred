package com.example.alfred;

import android.app.Application;

import com.example.alfred.model.Restaurant;

import java.util.ArrayList;

public class InfosApp extends Application {

    private ArrayList<Restaurant> restaurantList;

    @Override
    public void onCreate() {
        super.onCreate();
        restaurantList = new ArrayList<>();
    }

    public ArrayList<Restaurant> getRestaurantList() {
        return restaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList){
        this.restaurantList = restaurantList;
    }
}
