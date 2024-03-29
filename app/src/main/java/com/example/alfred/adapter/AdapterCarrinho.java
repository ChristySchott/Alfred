package com.example.alfred.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;

import java.util.List;

import modelDominio.PratoPedido;

public class AdapterCarrinho extends RecyclerView.Adapter<AdapterCarrinho.MyViewHolder> {

    private List<PratoPedido> listaPratosPedido;
    private ItemCarrinhoOnClickListener itemCarrinhoOnClickListener;

    public AdapterCarrinho(List<PratoPedido> listaPratosPedido, ItemCarrinhoOnClickListener itemCarrinhoOnClickListener) {
        this.listaPratosPedido = listaPratosPedido;
        this.itemCarrinhoOnClickListener = itemCarrinhoOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_carrinho, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        PratoPedido pratoPedido = listaPratosPedido.get(position);

        holder.quantidade.setText(pratoPedido.getQuantidadePratoPedido() + "x");
        holder.nome.setText(pratoPedido.getNomePrato());
        holder.preco.setText("R$ " + pratoPedido.getValorUnidadePratoPedidoString());

        if (itemCarrinhoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemCarrinhoOnClickListener.onClickItemCarrinho(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPratosPedido.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantidade;
        TextView nome;
        TextView preco;

        public MyViewHolder(View itemView) {
            super(itemView);

            quantidade = itemView.findViewById(R.id.txCarrinhoQuantidade);
            nome = itemView.findViewById(R.id.txCarrinhoNomePrato);
            preco = itemView.findViewById(R.id.txCarrinhoPrecoPrato);
        }
    }

    public interface ItemCarrinhoOnClickListener {
        void onClickItemCarrinho(View view, int position);
    }

}
