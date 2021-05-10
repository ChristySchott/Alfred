package com.example.alfred.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class AdapterMeusPedidos extends RecyclerView.Adapter<AdapterMeusPedidos.MyViewHolder> {

    private List<Pedido> listaPedidos;
    private Context contexto;
    AdapterPratoPedido adapterPratoPedido;

    public AdapterMeusPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_meus_pedidos, parent, false);
        this.contexto = parent.getContext();
        // Passamos o listItem como parâmetro para o MyViewHolder adaptar os dados
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Pedido pedido = listaPedidos.get(position);

        Bitmap imagem = getImage(pedido.getEmpresa().getImagemEmpresa());
        holder.imagemRestaurante.setImageBitmap(imagem);
        holder.nomeRestaurante.setText(pedido.getEmpresa().getNomeEmpresa());

        adapterPratoPedido = new AdapterPratoPedido(pedido.getListaPratosPedido());
        holder.rvPratoPedido.setLayoutManager(new LinearLayoutManager(this.contexto));
//        holder.rvPratoPedido.setHasFixedSize(true);
        holder.rvPratoPedido.setItemAnimator(new DefaultItemAnimator());
//        holder.rvPratoPedido.addItemDecoration(new DividerItemDecoration(this.contexto, LinearLayout.VERTICAL));
        holder.rvPratoPedido.setAdapter(adapterPratoPedido);
    }

    @Override
    public int getItemCount() {
        return listaPedidos.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomeRestaurante;
        ImageView imagemRestaurante;
        RecyclerView rvPratoPedido;

        public MyViewHolder(View itemView) {
            super(itemView);
            nomeRestaurante = itemView.findViewById(R.id.txMeusPedidosNomeRestaurante);
            imagemRestaurante = itemView.findViewById(R.id.ivMeusPedidosImagem);
            rvPratoPedido = itemView.findViewById(R.id.rvPratoPedido);
        }
    }

    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

}
