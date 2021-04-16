package com.example.alfred.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;

import com.example.alfred.adapter.AdapterPratos;
import modelDominio.Empresa;
import modelDominio.Prato;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class CardapioActivity extends AppCompatActivity {

    private List<Prato> listaPratos =  new ArrayList<>();
    Empresa empresaSelecionada;
    AdapterPratos adapterPratos;
    InformacoesApp informacoesApp;

    TextView txCardapioNome, txCardapioCidade, txCardapioEstado, txCardapioRua;
    RatingBar rbCardapioAvaliacao;
    RecyclerView rvPratos;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        // Iniciando componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Recuperar empresa selecionada
        Bundle bundle = getIntent().getExtras();
        if( bundle != null ){
            empresaSelecionada = (Empresa) bundle.getSerializable("empresa");
            txCardapioNome.setText(empresaSelecionada.getNomeEmpresa());
            // TODO - Setar essas infos após o join das tabelas
            // txCardapioCidade.setText(empresaSelecionada.getCidadeUsuario().getNomeCidade());
            // txCardapioEstado.setText(empresaSelecionada.getEstadoUsuario().getNomeEstado());
            // txCardapioRua.setText(empresaSelecionada.getRuaUsuario());
            rbCardapioAvaliacao.setNumStars(empresaSelecionada.getAvaliacaoEmpresa().getNotaAvaliacao());
        }

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Cardápio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);
                listaPratos = ccon.pratoListaEmpresa(empresaSelecionada.getCodEmpresa());
                if (listaPratos != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterPratos = new AdapterPratos(listaPratos, trataCliquePrato);
                            rvPratos.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvPratos.setHasFixedSize(true);
                            rvPratos.setItemAnimator(new DefaultItemAnimator());
                            rvPratos.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                            rvPratos.setAdapter(adapterPratos);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista de pratos!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        thread.start();

    }

    AdapterPratos.PratoOnClickListener trataCliquePrato = new AdapterPratos.PratoOnClickListener() {
        @Override
        public void onClickPrato(View view, int position) {
            confirmarQuantidade(position);
        }
    };


    public void iniciarComponentes() {
        txCardapioNome = findViewById(R.id.txCardapioNome);
        txCardapioCidade = findViewById(R.id.txCardapioCidade);
        txCardapioEstado = findViewById(R.id.txCardapioEstado);
        txCardapioRua = findViewById(R.id.txCardapioRua);
        rbCardapioAvaliacao = findViewById(R.id.rbCardapioAvaliacao);
        rvPratos = findViewById(R.id.rvPratos);
    }

    private void confirmarQuantidade(final int posicao){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Quantidade");
        builder.setMessage("Digite a quantidade");

        // TODO - MUDAR PARA BOTÕES
        final EditText editQuantidade = new EditText(this);
        editQuantidade.setText("1");

        builder.setView( editQuantidade );

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String quantidade = editQuantidade.getText().toString();

                // TODO - Construir prato pedido
                // Prato recuperado
                Prato pratoSelecionado = listaPratos.get(posicao);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
