package com.example.alfred.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;

import java.util.ArrayList;
import java.util.List;

import modelDominio.PratoPedido;

public class AdapterPratoPedido extends RecyclerView.Adapter<AdapterPratoPedido.MyViewHolder> {

    private List<PratoPedido> listaPratosPedido;
    public AdapterPratoPedido(List<PratoPedido> listaPratosPedido) {
        this.listaPratosPedido = listaPratosPedido;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_prato_pedido, parent, false);

        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        PratoPedido pratoPedido = listaPratosPedido.get(position);

        holder.quantidade.setText(pratoPedido.getQuantidadePratoPedidoString() + "x");
        holder.nomePrato.setText(pratoPedido.getNomePrato());
        holder.precoUnidade.setText(pratoPedido.getValorUnidadePratoPedidoString());

    }

    @Override
    public int getItemCount() {
        return this.listaPratosPedido.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantidade;
        TextView nomePrato;
        TextView precoUnidade;

        public MyViewHolder(View itemView) {
            super(itemView);

            quantidade = itemView.findViewById(R.id.txMeusPedidosQuantidade);
            nomePrato = itemView.findViewById(R.id.txMeusPedidosNomePrato);
            precoUnidade = itemView.findViewById(R.id.txMeusPedidosPrecoPrato);
        }
    }
}
