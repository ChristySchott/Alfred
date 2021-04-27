package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

import modelDominio.Pedido;
import modelDominio.PratoPedido;

public class CarrinhoActivity extends AppCompatActivity {

    AdapterCarrinho adapterCarrinho;
    RadioButton rbCarrinhoDinheiro, rbCarrinhoCartao;
    TextInputEditText txCarrinhoObservacao;
    RecyclerView rvPratosPedido;
    PratoPedido meuPratoPedido;
    Pedido pedido;
    InformacoesApp informacoesApp;
    Button btnCarrinhoConfirmarPedido, btnCarrinhoCancelarPedido;
    int codPedido = 0, formaPagamento = 0;
    String observacao = "";
    double valorTotal;

    private List<PratoPedido> pratosPedido = new ArrayList<>();
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

        btnCarrinhoCancelarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ConexaoController ccon = new ConexaoController(informacoesApp);
                        boolean okPedido = ccon.pedidoExcluir(codPedido);
                        if (okPedido) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(informacoesApp, "Pedido excluído com sucesso", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(informacoesApp, "Não foi possível excluir o pedido", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }

                    }
                });
                thread.start();
            }
        });

        btnCarrinhoConfirmarPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rbCarrinhoCartao.isChecked() && !rbCarrinhoDinheiro.isChecked()) {
                    Toast.makeText(informacoesApp, "Selecione a forma de pagamento", Toast.LENGTH_SHORT).show();
                } else {

                    if (!txCarrinhoObservacao.getText().toString().equals("")) {
                        observacao = txCarrinhoObservacao.getText().toString();
                    }

                    if (rbCarrinhoDinheiro.isChecked()) {
                        formaPagamento = 0;
                    } else if (rbCarrinhoCartao.isChecked()) {
                        formaPagamento = 1;
                    }

                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {

                            ConexaoController ccon = new ConexaoController(informacoesApp);

                            pedido = new Pedido(codPedido, 0, observacao, formaPagamento);

                            boolean okPedido = ccon.pedidoAlterar(pedido);
                            if (okPedido) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(informacoesApp, "Pedido realizado com sucesso", Toast.LENGTH_SHORT).show();

                                        Intent it = new Intent(CarrinhoActivity.this, CardapioActivity.class);
                                        startActivity(it);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(informacoesApp, "Não foi realizar o pedido", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }


                        }
                    });
                    thread.start();

                }
            }
        });
    }

    public void iniciarComponentes() {
        rbCarrinhoDinheiro = findViewById(R.id.rbCarrinhoDinheiro);
        rbCarrinhoCartao = findViewById(R.id.rbCarrinhoCartao);
        rvPratosPedido = findViewById(R.id.rvPratosPedido);
        txCarrinhoObservacao = findViewById(R.id.txCarrinhoObservacao);
        btnCarrinhoConfirmarPedido = findViewById(R.id.btnCarrinhoConfirmarPedido);
        btnCarrinhoCancelarPedido = findViewById(R.id.btnCarrinhoCancelarPedido);
    }

}
