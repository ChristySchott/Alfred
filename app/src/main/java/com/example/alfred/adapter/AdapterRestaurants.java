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

public class AdapterRestaurants extends RecyclerView.Adapter<AdapterRestaurants.MyViewHolder> {

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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView name;
            RatingBar rating;
            TextView category;
            TextView averagePrice;

            // TODO - Verificar se nomes não repetem
            public MyViewHolder(View itemView) {
                super(itemView);


                name = itemView.findViewById(R.id.txName);
                rating = itemView.findViewById(R.id.rbRating);
                category = itemView.findViewById(R.id.txCategory);
                averagePrice = itemView.findViewById(R.id.txAveragePrice);
            }
        }
}
