package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;
import com.example.alfred.model.Dish;

import java.util.List;

public class AdapterDishes extends RecyclerView.Adapter<AdapterDishes.MyViewHolder> {

    private List<Dish> dishList;

    public AdapterDishes(List<Dish> list) {
        this.dishList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_dishes, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Dish dish = dishList.get(position);
        holder.name.setText(dish.getName());
        holder.description.setText(dish.getDescription());
        holder.price.setText(dish.getPrice().toString());
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        TextView price;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txDishName);
            description = itemView.findViewById(R.id.txDishDescription);
            price = itemView.findViewById(R.id.txDishPrice);
        }
    }
}
