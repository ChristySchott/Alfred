package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import adapter.AdapterCarrinho;
import modelDominio.PratoPedido;

public class CarrinhoActivity extends AppCompatActivity {

    private List<PratoPedido> pratosPedido;
    AdapterCarrinho adapterCarrinho;
    RadioButton rbCarrinhoDinheiro, rbCarrinhoCartao;
    TextInputEditText txCarrinhoObservacao;
    RecyclerView rvPratosPedido;
    InformacoesApp informacoesApp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Configuração inicial dos components
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Carrinho");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);
                // TODO - Adicionar método nos controllers - pratosPedidoLista();
                // pratosPedido = ccon.empresasAbertasLista();
                if (pratosPedido != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterCarrinho = new AdapterCarrinho(pratosPedido, trataCliquePratoPedido);
                            rvPratosPedido.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvPratosPedido.setHasFixedSize(true);
                            rvPratosPedido.setItemAnimator(new DefaultItemAnimator());
                            rvPratosPedido.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                            rvPratosPedido.setAdapter(adapterCarrinho);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista das empresas abertas!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        thread.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void iniciarComponentes() {
        rbCarrinhoDinheiro = findViewById(R.id.rbCarrinhoDinheiro);
        rbCarrinhoCartao = findViewById(R.id.rbCarrinhoCartao);
        rvPratosPedido = findViewById(R.id.rvPratosPedido);
        txCarrinhoObservacao = findViewById(R.id.txCarrinhoObservacao);
    }

    AdapterCarrinho.ItemCarrinhoOnClickListener trataCliquePratoPedido = new AdapterCarrinho.ItemCarrinhoOnClickListener() {
        @Override
        public void onClickItemCarrinho(View view, int position) {
            PratoPedido meuPratoPedido = pratosPedido.get(position);
        }
    };

}
