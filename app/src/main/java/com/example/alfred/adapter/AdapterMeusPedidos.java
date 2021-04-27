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

import modelDominio.Pedido;

public class AdapterMeusPedidos extends RecyclerView.Adapter<AdapterMeusPedidos.MyViewHolder> {

    private List<Pedido> listaPedidos;
    private MeuPedidoOnClickListener meuPedidoOnClickListener;

    public AdapterMeusPedidos(List<Pedido> listaPedidos, MeuPedidoOnClickListener meuPedidoOnClickListener) {
        this.listaPedidos = listaPedidos;
        this.meuPedidoOnClickListener = meuPedidoOnClickListener;
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
        Pedido pedido = listaPedidos.get(position);

        // TODO - BUSCAR PRATO PEDIDO DE ACORDO COM PEDIDO OU RECEBER PRATOPEDIDO AQUI?

        if (meuPedidoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    meuPedidoOnClickListener.onClickMeuPedido(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantidade;
        TextView nomeRestaurante;
        TextView nomePrato;
        TextView preco;
        ImageView imagemRestaurante;

        public MyViewHolder(View itemView) {
            super(itemView);

            quantidade = itemView.findViewById(R.id.txMeusPedidosQuantidade);
            nomePrato = itemView.findViewById(R.id.txMeusPedidosNomePrato);
            preco = itemView.findViewById(R.id.txMeusCarrinhosPrecoPrato);
            nomeRestaurante = itemView.findViewById(R.id.txMeusPedidosNomeRestaurante);
            imagemRestaurante = itemView.findViewById(R.id.ivMeusPedidosImagem);
        }
    }

    public interface MeuPedidoOnClickListener {
        void onClickMeuPedido(View view, int position);
    }

}
