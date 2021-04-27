package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.adapter.AdapterMeusPedidos;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Pedido;

public class MeusPedidosActivity extends AppCompatActivity {

    RecyclerView rvEmAnalise, rvAprovado, rvNegado;
    InformacoesApp informacoesApp;
    Pedido pedido;
    AdapterMeusPedidos adapterMeusPedidos;

    private List<Pedido> pratosPedido = new ArrayList<>();
    // TODO - O clique deve ser no botão com ícone
    AdapterMeusPedidos.MeuPedidoOnClickListener trataCliquePedido = new AdapterMeusPedidos.MeuPedidoOnClickListener() {
        @Override
        public void onClickMeuPedido(View view, int position) {
            pedido = pratosPedido.get(position);

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    ConexaoController ccon = new ConexaoController(informacoesApp);
                    boolean okPedido = true;
                    if (okPedido) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    adapterMeusPedidos = new AdapterMeusPedidos(pratosPedido, trataCliquePedido);
                                    rvEmAnalise.setLayoutManager(new LinearLayoutManager(informacoesApp));
                                    rvEmAnalise.setHasFixedSize(true);
                                    rvEmAnalise.setItemAnimator(new DefaultItemAnimator());
                                    rvEmAnalise.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                                    rvEmAnalise.setAdapter(adapterMeusPedidos);
                                }
                            });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível excluir o prato!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    if (okPedido) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterMeusPedidos = new AdapterMeusPedidos(pratosPedido, trataCliquePedido);
                                rvAprovado.setLayoutManager(new LinearLayoutManager(informacoesApp));
                                rvAprovado.setHasFixedSize(true);
                                rvAprovado.setItemAnimator(new DefaultItemAnimator());
                                rvAprovado.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                                rvAprovado.setAdapter(adapterMeusPedidos);
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível excluir o prato!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    if (okPedido) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                adapterMeusPedidos = new AdapterMeusPedidos(pratosPedido, trataCliquePedido);
                                rvNegado.setLayoutManager(new LinearLayoutManager(informacoesApp));
                                rvNegado.setHasFixedSize(true);
                                rvNegado.setItemAnimator(new DefaultItemAnimator());
                                rvNegado.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                                rvNegado.setAdapter(adapterMeusPedidos);
                            }
                        });
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
        setContentView(R.layout.activity_meus_pedidos);


        // Iniciando componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Meus Pedidos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void iniciarComponentes() {
        rvEmAnalise = findViewById(R.id.rvEmAnalise);
        rvAprovado = findViewById(R.id.rvAprovado);
        rvNegado = findViewById(R.id.rvNegado);
    }

}
