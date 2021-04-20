package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;

import java.util.List;

import modelDominio.Categoria;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.MyViewHolder> {

    private List<Categoria> listaCategorias;
    private AdapterCategorias.CategoriaOnClickListener categoriaOnClickListener;

    public AdapterCategorias(List<Categoria> listaCategorias, AdapterCategorias.CategoriaOnClickListener categoriaOnClickListener) {
        this.listaCategorias = listaCategorias;
        this.categoriaOnClickListener = categoriaOnClickListener;
    }

    @NonNull
    @Override
    public AdapterCategorias.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_categorias, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new AdapterCategorias.MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterCategorias.MyViewHolder holder, final int position) {
        Categoria categoria = listaCategorias.get(position);
        holder.nome.setText(categoria.getNomeCategoria());

        if (categoriaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    categoriaOnClickListener.onClickCategoria(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaCategorias.size();
    }

    public interface CategoriaOnClickListener {
        void onClickCategoria(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagem;
        TextView nome;

        public MyViewHolder(View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.ivCategoriaImagem);
            nome = itemView.findViewById(R.id.txCategoriaNome);
        }
    }
}
