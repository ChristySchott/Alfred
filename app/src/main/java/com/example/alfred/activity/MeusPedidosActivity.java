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
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.adapter.AdapterMeusPedidos;
import com.example.alfred.adapter.AdapterMeusPedidosAprovados;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Avaliacao;
import modelDominio.Empresa;
import modelDominio.Pedido;

public class MeusPedidosActivity extends AppCompatActivity {

    RecyclerView rvEmAnalise, rvAprovado, rvNegado;
    InformacoesApp informacoesApp;
    AdapterMeusPedidos adapterMeusPedidos;
    AdapterMeusPedidosAprovados adapterMeusPedidosAprovados;
    Avaliacao avaliacao;
    Empresa empresa;
    int codCliente, notaAvaliacao;
    String comentarioAvaliacao;

    // Componentes do Dialog
    TextView txAvaliarEmpresaComentario;
    RatingBar rbAvaliarEmpresaNota;

    private List<Pedido> pedidosAnalise = new ArrayList<>();
    private List<Pedido> pedidosAprovados = new ArrayList<>();
    private List<Pedido> pedidosReprovados = new ArrayList<>();

    AdapterMeusPedidosAprovados.PedidoAprovadoOnClickListener trataCliquePedidoAprovado = new AdapterMeusPedidosAprovados.PedidoAprovadoOnClickListener() {
        @Override
        public void onClickPedidoAprovado(View view, int position) {
            avaliarEmpresa(position);
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);

        // Iniciando componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        codCliente = informacoesApp.cliente.getCodCliente();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Meus Pedidos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);

                pedidosAnalise = ccon.pedidoAnaliseLista(codCliente);
                if (pedidosAnalise != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterMeusPedidos = new AdapterMeusPedidos(pedidosAnalise);
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
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível listar os pedidos em análise!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                pedidosAprovados = ccon.pedidoAprovadoLista(codCliente);
                if (pedidosAprovados != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterMeusPedidosAprovados = new AdapterMeusPedidosAprovados(pedidosAprovados, trataCliquePedidoAprovado);
                            rvAprovado.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvAprovado.setHasFixedSize(true);
                            rvAprovado.setItemAnimator(new DefaultItemAnimator());
                            rvAprovado.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                            rvAprovado.setAdapter(adapterMeusPedidosAprovados);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível listar os pedidos aprovados!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                pedidosReprovados = ccon.pedidoReprovadoLista(codCliente);
                if (pedidosReprovados != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterMeusPedidos = new AdapterMeusPedidos(pedidosReprovados);
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
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível listar os pedidos recusados!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        thread.start();
    }


    public void iniciarComponentes() {
        rvEmAnalise = findViewById(R.id.rvEmAnalise);
        rvAprovado = findViewById(R.id.rvAprovado);
        rvNegado = findViewById(R.id.rvNegado);
    }

    private void avaliarEmpresa(final int posicao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final Pedido pedidoSelecionado = pedidosAprovados.get(posicao);
        builder.setTitle("Avaliar " + pedidoSelecionado.getEmpresa().getNomeEmpresa());

        empresa = pedidoSelecionado.getEmpresa();

        ViewGroup viewGroup = findViewById(R.id.content_meus_pedidos);
        View view = getLayoutInflater().inflate(R.layout.fragment_dialog_avaliacao, viewGroup, false);

        builder.setView(view);

        txAvaliarEmpresaComentario = view.findViewById(R.id.txAvaliarEmpresaComentario);
        rbAvaliarEmpresaNota = view.findViewById(R.id.rbAvaliarEmpresa);

        rbAvaliarEmpresaNota.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                notaAvaliacao = Math.round(rating);
            }
        });

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (!txAvaliarEmpresaComentario.getText().toString().equals("")) {
                    comentarioAvaliacao = txAvaliarEmpresaComentario.getText().toString();
                } else {
                    comentarioAvaliacao = "";
                }

                avaliacao = new Avaliacao(comentarioAvaliacao, notaAvaliacao, informacoesApp.cliente, empresa);

                Thread thread = new Thread() {
                    @Override
                    public void run() {

                        ConexaoController ccont = new ConexaoController(informacoesApp);

                        boolean okAvaliacao = ccont.avaliacaoInserir(avaliacao);

                        if (okAvaliacao) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MeusPedidosActivity.this, "Avaliação realizada com sucesso!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MeusPedidosActivity.this, "Falha ao realizar a avaliação!", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                };
                thread.start();
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
