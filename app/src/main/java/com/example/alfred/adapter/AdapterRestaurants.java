package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;
import com.example.alfred.model.Restaurant;

import java.util.List;

public class AdapterRestaurants extends RecyclerView.Adapter<AdapterRestaurants.MyViewHolder> {

    private List<Restaurant> restaurantList;

    public AdapterRestaurants(List<Restaurant> list) {
        this.restaurantList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_restaurants, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Restaurant restaurant = restaurantList.get(position);
        holder.name.setText(restaurant.getName());
        holder.rating.setNumStars(restaurant.getRating());
        holder.category.setText(restaurant.getCategory());
        holder.averagePrice.setText(restaurant.getAveragePrice().toString());
    }

    @Override
    public int getItemCount() {
        return restaurantList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView name;
            RatingBar rating;
            TextView category;
            TextView averagePrice;

            public MyViewHolder(View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.ivRestaurantImage);
                name = itemView.findViewById(R.id.txRestaurantName);
                rating = itemView.findViewById(R.id.rbRestaurantRating);
                category = itemView.findViewById(R.id.txRestaurantName);
                averagePrice = itemView.findViewById(R.id.txRestaurantAveragePrice);
            }
        }
}
