package com.example.alfred.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.adapter.AdapterPratos;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Empresa;
import modelDominio.Pedido;
import modelDominio.Prato;
import modelDominio.PratoPedido;

public class CardapioActivity extends AppCompatActivity {

    Empresa empresaSelecionada;
    PratoPedido pratoPedido;
    Pedido pedido;
    List<PratoPedido> pratoPedidoLista = new ArrayList<>();
    AdapterPratos adapterPratos;
    InformacoesApp informacoesApp;
    TextView txCardapioNome, txCardapioCidade, txCardapioEstado, txCardapioRua, txValorTotal;
    RatingBar rbCardapioAvaliacao;
    RecyclerView rvPratos;

    // Componentes do Dialog
    TextView txQuantidadePreco, txQuantidade;
    Button btnQuantidadeDiminuir, btnQuantidadeAumentar;
    Integer quantidadeSelecionada = 1;
    Double valorTotalCarrinho = 0.00;

    private List<Prato> listaPratos = new ArrayList<>();
    AdapterPratos.PratoOnClickListener trataCliquePrato = new AdapterPratos.PratoOnClickListener() {
        @Override
        public void onClickPrato(View view, int position) {
            confirmarQuantidade(position);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        // Iniciando componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        if (informacoesApp.empresa != null) {
            empresaSelecionada = (Empresa) informacoesApp.empresa;
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

    public void iniciarComponentes() {
        txCardapioNome = findViewById(R.id.txCardapioNome);
        txCardapioCidade = findViewById(R.id.txCardapioCidade);
        txCardapioEstado = findViewById(R.id.txCardapioEstado);
        txCardapioRua = findViewById(R.id.txCardapioRua);
        rbCardapioAvaliacao = findViewById(R.id.rbCardapioAvaliacao);
        rvPratos = findViewById(R.id.rvPratos);
        txValorTotal = findViewById(R.id.txValorTotal);
    }

    private void confirmarQuantidade(final int posicao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final Prato pratoSelecionado = listaPratos.get(posicao);
        builder.setTitle(pratoSelecionado.getNomePrato());

        quantidadeSelecionada = 1;

        final Double pratoPreco = pratoSelecionado.getValorPrato();

        ViewGroup viewGroup = findViewById(R.id.content_cardapio);
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_quantidade, viewGroup, false);

        builder.setView(view);

        txQuantidadePreco = view.findViewById(R.id.txQuantidadePreco);
        txQuantidade = view.findViewById(R.id.txQuantidade);
        btnQuantidadeDiminuir = view.findViewById(R.id.btnQuantidadeDiminuir);
        btnQuantidadeAumentar = view.findViewById(R.id.btnQuantidadeAumentar);

        txQuantidadePreco.setText("R$ " + pratoSelecionado.getValorPratoString());

        btnQuantidadeDiminuir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantidadeSelecionada > 1) {
                    quantidadeSelecionada = quantidadeSelecionada - 1;
                    txQuantidade.setText(quantidadeSelecionada.toString());
                }
            }
        });


        btnQuantidadeAumentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantidadeSelecionada = quantidadeSelecionada + 1;
                txQuantidade.setText(quantidadeSelecionada.toString());
            }
        });

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                pratoPedido = new PratoPedido(quantidadeSelecionada, pratoSelecionado, pratoSelecionado.getValorPrato());
                if (pratoPedido != null) {
                    pratoPedidoLista.add(pratoPedido);
                    valorTotalCarrinho = valorTotalCarrinho + (pratoPreco * quantidadeSelecionada);
                    txValorTotal.setText("R$ " + valorTotalCarrinho.toString());
                } else {
                    Toast.makeText(CardapioActivity.this, "Erro ao adicionar o prato!", Toast.LENGTH_SHORT).show();
                }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cardapio, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuPedido:
                if (valorTotalCarrinho < 1) {
                    Toast.makeText(CardapioActivity.this, "Ao menos um prato deve ser adicionado ao pedido!", Toast.LENGTH_SHORT).show();
                } else {
                    pedido = new Pedido(informacoesApp.cliente, empresaSelecionada);
                    Thread thread = new Thread() {
                        @Override
                        public void run() {

                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            boolean okPedido = ccont.pedidoInserir(pedido);

                            if (okPedido) {
                                int codPedido = ccont.getCodPedido();

                                for (int x = 0; x < pratoPedidoLista.size(); x++) {
                                    PratoPedido meuPratoPedido = pratoPedidoLista.get(x);
                                    pratoPedido = new PratoPedido(meuPratoPedido.getQuantidadePratoPedido(), meuPratoPedido.getPrato(), meuPratoPedido.getValorUnidadePratoPedido(), codPedido);
                                    ccont.pratoPedidoInserir(pratoPedido);
                                }

                                Intent it = new Intent(CardapioActivity.this, CarrinhoActivity.class);
                                it.putExtra("total", valorTotalCarrinho);
                                it.putExtra("codPedido", codPedido);
                                startActivity(it);
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CardapioActivity.this, "Erro ao criar o pedido!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                }

        }

        return super.onOptionsItemSelected(item);
    }


}
