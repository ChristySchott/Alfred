package com.example.alfred.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.adapter.AdapterCarrinho;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import modelDominio.PratoPedido;

public class CarrinhoActivity extends AppCompatActivity {

    AdapterCarrinho adapterCarrinho;
    RadioButton rbCarrinhoDinheiro, rbCarrinhoCartao;
    TextInputEditText txCarrinhoObservacao;
    RecyclerView rvPratosPedido;
    PratoPedido meuPratoPedido;
    InformacoesApp informacoesApp;
    int codPedido = 0;
    double valorTotal;

    private List<PratoPedido> pratosPedido;
    // TODO - O clique deve ser no botão com ícone
    AdapterCarrinho.ItemCarrinhoOnClickListener trataCliquePratoPedido = new AdapterCarrinho.ItemCarrinhoOnClickListener() {
        @Override
        public void onClickItemCarrinho(View view, int position) {
            meuPratoPedido = pratosPedido.get(position);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    ConexaoController ccon = new ConexaoController(informacoesApp);
                    boolean okPedido = ccon.pratoPedidoExcluir(meuPratoPedido);
                    if (okPedido) {
                        pratosPedido = ccon.pratoPedidoCarrinhoLista(codPedido);
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
                                    Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista de pratos do pedido!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível excluir o prato!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                }
            });
            thread.start();
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Configuração inicial dos components
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Carrinho");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recuperar código do pedido e valor total
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            codPedido = (int) bundle.getSerializable("codPedido");
            valorTotal = (double) bundle.getSerializable("total");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);
                pratosPedido = ccon.pratoPedidoCarrinhoLista(codPedido);
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
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista de pratos do pedido!", Toast.LENGTH_SHORT).show();
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

}
