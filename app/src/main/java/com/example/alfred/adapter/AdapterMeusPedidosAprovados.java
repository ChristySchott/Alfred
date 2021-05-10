package com.example.alfred.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.R;

import java.util.List;

import modelDominio.Pedido;

public class AdapterMeusPedidosAprovados extends RecyclerView.Adapter<AdapterMeusPedidosAprovados.MyViewHolder> {

    private List<Pedido> listaPedidosAprovados;
    private PedidoAprovadoOnClickListener pedidoAprovadoOnClickListener;
    private Context contexto;
    AdapterPratoPedido adapterPratoPedido;

    public AdapterMeusPedidosAprovados(List<Pedido> listaPedidosAprovados, PedidoAprovadoOnClickListener pedidoAprovadoOnClickListener) {
        this.listaPedidosAprovados = listaPedidosAprovados;
        this.pedidoAprovadoOnClickListener = pedidoAprovadoOnClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_meus_pedidos_aprovados, parent, false);
        this.contexto = parent.getContext();
        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Pedido pedido = listaPedidosAprovados.get(position);
        System.out.println("pedido");
        System.out.println(pedido.toString());

        Bitmap imagem = getImage(pedido.getEmpresa().getImagemEmpresa());
        holder.imagemRestaurante.setImageBitmap(imagem);
        holder.nomeRestaurante.setText(pedido.getEmpresa().getNomeEmpresa());

        System.out.println("pedido.getListaPratosPedido()");
        System.out.println(pedido.getListaPratosPedido());
        adapterPratoPedido = new AdapterPratoPedido(pedido.getListaPratosPedido());
        holder.rvPratoPedido.setLayoutManager(new LinearLayoutManager(this.contexto));
//        holder.rvPratoPedido.setHasFixedSize(true);
        holder.rvPratoPedido.setItemAnimator(new DefaultItemAnimator());
//        holder.rvPratoPedido.addItemDecoration(new DividerItemDecoration(this.contexto, LinearLayout.VERTICAL));
        holder.rvPratoPedido.setAdapter(adapterPratoPedido);

        if (pedidoAprovadoOnClickListener != null) {
            holder.btnAvaliar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pedidoAprovadoOnClickListener.onClickPedidoAprovado(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return this.listaPedidosAprovados.size();
    }

    public interface PedidoAprovadoOnClickListener {
        void onClickPedidoAprovado(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeRestaurante;
        ImageView imagemRestaurante;
        RecyclerView rvPratoPedido;
        Button btnAvaliar;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomeRestaurante = itemView.findViewById(R.id.txMeusPedidosAprovadosNomeRestaurante);
            imagemRestaurante = itemView.findViewById(R.id.ivMeusPedidosAprovadosImagem);
            rvPratoPedido = itemView.findViewById(R.id.rvPratoPedidoAprovado);
            btnAvaliar = itemView.findViewById(R.id.btnMeusPedidosAprovados);
        }
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


}
