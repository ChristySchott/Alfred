package com.example.alfred;

import android.app.Application;

import com.example.alfred.model.Dish;
import com.example.alfred.model.Restaurant;

import java.util.ArrayList;

public class InfosApp extends Application {

    private ArrayList<Restaurant> restaurantList;
    private ArrayList<Dish> dishList;

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

    public ArrayList<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(ArrayList<Dish> dishList) {
        this.dishList = dishList;
    }
}
