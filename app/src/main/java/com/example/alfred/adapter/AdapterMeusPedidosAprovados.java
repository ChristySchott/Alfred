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

public class AdapterMeusPedidosAprovados extends RecyclerView.Adapter<AdapterMeusPedidosAprovados.MyViewHolder> {

    private List<Pedido> listaPedidosAprovados;
    private PedidoAprovadoOnClickListener pedidoAprovadoOnClickListener;

    public AdapterMeusPedidosAprovados(List<Pedido> listaPedidosAprovados, PedidoAprovadoOnClickListener pedidoAprovadoOnClickListener) {
        this.listaPedidosAprovados = listaPedidosAprovados;
        this.pedidoAprovadoOnClickListener = pedidoAprovadoOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_meus_pedidos_aprovados, parent, false);

        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Pedido pedido = listaPedidosAprovados.get(position);

        // TODO - BUSCAR PRATO PEDIDO DE ACORDO COM PEDIDO OU RECEBER PRATOPEDIDO AQUI?
        // TODO - ADICIONAR IMAGEMRESTAURANTE
        holder.quantidade.setText(pedido.getPratoPedido().getQuantidadePratoPedido() + "x");
        holder.nomeRestaurante.setText(pedido.getEmpresa().getNomeEmpresa());
        holder.preco.setText("R$ " + pedido.getPratoPedido().getValorUnidadePratoPedidoString());
        // holder.nomePrato.setText(pedido.getPratoPedido().getPrato().getNomePrato());

        if (pedidoAprovadoOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pedidoAprovadoOnClickListener.onClickPedidoAprovado(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaPedidosAprovados.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView quantidade;
        TextView nomeRestaurante;
        TextView nomePrato;
        TextView preco;
        ImageView imagemRestaurante;

        public MyViewHolder(View itemView) {
            super(itemView);

            quantidade = itemView.findViewById(R.id.txMeusPedidosAprovadosQuantidade);
            nomePrato = itemView.findViewById(R.id.txMeusPedidosAprovadosNomePrato);
            preco = itemView.findViewById(R.id.txMeusPedidosAprovadosPrecoPrato);
            nomeRestaurante = itemView.findViewById(R.id.txMeusPedidosAprovadosNomeRestaurante);
            imagemRestaurante = itemView.findViewById(R.id.ivMeusPedidosAprovadosImagem);
        }
    }

    public interface PedidoAprovadoOnClickListener {
        void onClickPedidoAprovado(View view, int position);
    }

}
