package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import modelDominio.Prato;
import modelDominio.PratoPedido;

public class CarrinhoActivity extends AppCompatActivity {

    AdapterCarrinho adapterCarrinho;
    RadioButton rbCarrinhoDinheiro, rbCarrinhoCartao;
    TextInputEditText txCarrinhoObservacao;
    RecyclerView rvPratosPedido;
    Pedido pedido;
    InformacoesApp informacoesApp;
    Button btnCarrinhoConfirmarPedido, btnCarrinhoCancelarPedido;
    int formaPagamento = 0;
    String observacao = "";

    private List<PratoPedido> pratosPedido = new ArrayList<>();


    // TODO - O clique deve ser no botão com ícone
    AdapterCarrinho.ItemCarrinhoOnClickListener trataCliquePratoPedido = new AdapterCarrinho.ItemCarrinhoOnClickListener() {
        @Override
        public void onClickItemCarrinho(View view, int position) {
            pratosPedido.remove(pratosPedido.get(position));
            adapterCarrinho = new AdapterCarrinho(pratosPedido, trataCliquePratoPedido);
            rvPratosPedido.setLayoutManager(new LinearLayoutManager(informacoesApp));
            rvPratosPedido.setHasFixedSize(true);
            rvPratosPedido.setItemAnimator(new DefaultItemAnimator());
            rvPratosPedido.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
            rvPratosPedido.setAdapter(adapterCarrinho);
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
        Toolbar toolbar = findViewById(R.id.toolbarMaterial);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Recuperar pedido e lista de pratoPedido
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            pratosPedido = (ArrayList<PratoPedido>) bundle.getSerializable("pratoPedidoLista");
            pedido = (Pedido) bundle.getSerializable("pedido");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);

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
                Intent it = new Intent(CarrinhoActivity.this, CardapioActivity.class);
                startActivity(it);
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


                            pedido.setObservacaoPedido(observacao);
                            pedido.setFormaPagamentoPedido(formaPagamento);

                            boolean okPedido = ccon.pedidoInserir(pedido);

                            if (okPedido) {
                                int codPedido = ccon.getCodPedido();
                                for (int x = 0; x < pratosPedido.size(); x++) {
                                    PratoPedido meuPratoPedido = pratosPedido.get(x);
                                    meuPratoPedido.setCodPedido(codPedido);
                                    ccon.pratoPedidoInserir(meuPratoPedido);
                                }
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
