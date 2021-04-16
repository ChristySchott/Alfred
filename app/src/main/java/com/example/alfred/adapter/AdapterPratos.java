package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;
import modelDominio.Prato;

import java.util.List;

public class AdapterPratos extends RecyclerView.Adapter<AdapterPratos.MyViewHolder> {

    private List<Prato> listaPratos;
    private PratoOnClickListener pratoOnClickListener;

    public AdapterPratos(List<Prato> listaPratos, PratoOnClickListener pratoOnClickListener) {
        this.listaPratos = listaPratos;
        this.pratoOnClickListener = pratoOnClickListener;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Prato prato = listaPratos.get(position);
        holder.nome.setText(prato.getNomePrato());
        holder.description.setText(prato.getDescricaoPrato());
        holder.preco.setText(prato.getValorPratoString());

        if (pratoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pratoOnClickListener.onClickPrato(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPratos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome;
        TextView description;
        TextView preco;

        public MyViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.txPratoNome);
            description = itemView.findViewById(R.id.txPratoDescricao);
            preco = itemView.findViewById(R.id.txPratoPreco);
        }
    }

    public interface PratoOnClickListener {
        void onClickPrato(View view, int position);
    }

}
