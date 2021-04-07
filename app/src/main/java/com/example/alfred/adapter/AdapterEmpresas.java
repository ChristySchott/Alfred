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
import com.example.alfred.modelDominio.Empresa;

import java.util.List;

public class AdapterEmpresas extends RecyclerView.Adapter<AdapterEmpresas.MyViewHolder> {

    private List<Empresa> empresaList;

    public AdapterEmpresas(List<Empresa> list) {
        this.empresaList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_empresas, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Empresa empresa = empresaList.get(position);
        holder.nome.setText(empresa.getNomeEmpresa());
        holder.avaliacao.setNumStars(empresa.getNotaAvaliacao());
        holder.categoria.setText(empresa.getNomeCategoria());
        holder.precoMedio.setText(empresa.getNomeEmpresa());
    }

    @Override
    public int getItemCount() {
        return empresaList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

            ImageView image;
            TextView nome;
            RatingBar avaliacao;
            TextView categoria;
            TextView precoMedio;

            public MyViewHolder(View itemView) {
                super(itemView);

                image = itemView.findViewById(R.id.ivRestaurantImage);
                nome = itemView.findViewById(R.id.txRestaurantName);
                avaliacao = itemView.findViewById(R.id.rbRestaurantRating);
                categoria = itemView.findViewById(R.id.txRestaurantName);
                precoMedio = itemView.findViewById(R.id.txRestaurantAveragePrice);
            }
        }
}
