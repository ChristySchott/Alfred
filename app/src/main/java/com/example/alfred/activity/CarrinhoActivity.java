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
    InformacoesApp informacoesApp;
    private List<PratoPedido> pratosPedido;
    AdapterCarrinho.ItemCarrinhoOnClickListener trataCliquePratoPedido = new AdapterCarrinho.ItemCarrinhoOnClickListener() {
        @Override
        public void onClickItemCarrinho(View view, int position) {
            PratoPedido meuPratoPedido = pratosPedido.get(position);
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

    // TODO - Construir Alerta
    /*

   private void confirmarPedido() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Selecione um método de pagamento");

        CharSequence[] itens = new CharSequence[]{
          "Dinheiro", "Máquina cartão"
        };
        builder.setSingleChoiceItems(itens, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                metodoPagamento = which;
            }
        });

        final EditText editObservacao = new EditText(this);
        editObservacao.setHint("Digite uma observação");
        builder.setView( editObservacao );

        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String observacao = editObservacao.getText().toString();
                pedidoRecuperado.setMetodoPagamento( metodoPagamento );
                pedidoRecuperado.setObservacao( observacao );
                pedidoRecuperado.setStatus("confirmado");
                pedidoRecuperado.confimar();
                pedidoRecuperado.remover();
                pedidoRecuperado = null;

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
     */

}
