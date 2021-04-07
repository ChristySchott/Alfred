package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;
import com.example.alfred.modelDominio.Prato;

import java.util.List;

public class AdapterPratos extends RecyclerView.Adapter<AdapterPratos.MyViewHolder> {

    private List<Prato> pratoList;

    public AdapterPratos(List<Prato> list) {
        this.pratoList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_pratos, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Prato prato = pratoList.get(position);
        holder.name.setText(prato.getNomePrato());
        holder.description.setText(prato.getDescricaoPrato());
        holder.price.setText(prato.getValorPratoString());
    }

    @Override
    public int getItemCount() {
        return pratoList.size();
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
