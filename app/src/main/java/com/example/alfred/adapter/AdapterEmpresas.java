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

import java.util.List;

import modelDominio.Empresa;

public class AdapterEmpresas extends RecyclerView.Adapter<AdapterEmpresas.MyViewHolder> {

    private List<Empresa> listaEmpresas;
    private EmpresaOnClickListener empresaOnClickListener;

    public AdapterEmpresas(List<Empresa> listaEmpresas, EmpresaOnClickListener empresaOnClickListener) {
        this.listaEmpresas = listaEmpresas;
        this.empresaOnClickListener = empresaOnClickListener;
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
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Empresa empresa = listaEmpresas.get(position);
        holder.nome.setText(empresa.getNomeEmpresa());
        holder.avaliacao.setNumStars(empresa.getAvaliacaoEmpresa().getNotaAvaliacao());
        holder.categoria.setText(empresa.getCategoriaEmpresa().getNomeCategoria());
        holder.precoMedio.setText("R$ " + empresa.getPrecoMedioString());

        if (empresaOnClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    empresaOnClickListener.onClickEmpresa(holder.itemView, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listaEmpresas.size();
    }

    public interface EmpresaOnClickListener {
        void onClickEmpresa(View view, int position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView nome;
        RatingBar avaliacao;
        TextView categoria;
        TextView precoMedio;

        public MyViewHolder(View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.ivEmpresaImagem);
            nome = itemView.findViewById(R.id.txEmpresaNome);
            avaliacao = itemView.findViewById(R.id.rbEmpresaAvaliacao);
            categoria = itemView.findViewById(R.id.txEmpresaCategoria);
            precoMedio = itemView.findViewById(R.id.txEmpresaPrecoMedio);
        }
    }
}
