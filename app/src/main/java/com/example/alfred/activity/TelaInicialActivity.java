package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;

import java.util.ArrayList;
import java.util.List;

import com.example.alfred.adapter.AdapterEmpresas;
import com.google.android.material.appbar.MaterialToolbar;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import modelDominio.Empresa;

public class TelaInicialActivity extends AppCompatActivity {

    AdapterEmpresas adapterEmpresas;
    InformacoesApp informacoesApp;
    RecyclerView rvEmpresasAbertas, rvEmpresasFechadas;
    MaterialSearchView searchView;

    private List<Empresa> listaEmpresasAbertas = new ArrayList<>();

    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaAberta = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasAbertas.get(position);
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            it.putExtra("empresa", minhaEmpresa);
            startActivity(it);
        }
    };

    private List<Empresa> listaEmpresasFechadas = new ArrayList<>();
    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaFechada = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasFechadas.get(position);
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            it.putExtra("empresa", minhaEmpresa);
            startActivity(it);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);

        // Iniciar Componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        // TODO - Pegar o nome do cliente
        toolbar.setTitle("Bernardo Haab");
        setSupportActionBar(toolbar);

        //Configuração do search view
        searchView.setHint("pesquisar empresas");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Adicionar filtros as empresas
                return true;
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);
                listaEmpresasAbertas = ccon.empresasAbertasLista();
                if (listaEmpresasAbertas != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterEmpresas = new AdapterEmpresas(listaEmpresasAbertas, trataCliqueEmpresaAberta);
                            rvEmpresasAbertas.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvEmpresasAbertas.setHasFixedSize(true);
                            rvEmpresasAbertas.setItemAnimator(new DefaultItemAnimator());
                            rvEmpresasAbertas.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                            rvEmpresasAbertas.setAdapter(adapterEmpresas);
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

                listaEmpresasFechadas = ccon.empresasFechadasLista();
                if (listaEmpresasFechadas != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterEmpresas = new AdapterEmpresas(listaEmpresasFechadas, trataCliqueEmpresaFechada);
                            rvEmpresasFechadas.setLayoutManager(new LinearLayoutManager(informacoesApp));
                            rvEmpresasFechadas.setHasFixedSize(true);
                            rvEmpresasFechadas.setItemAnimator(new DefaultItemAnimator());
                            rvEmpresasFechadas.addItemDecoration(new DividerItemDecoration(informacoesApp, LinearLayout.VERTICAL));
                            rvEmpresasFechadas.setAdapter(adapterEmpresas);
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista das empresas fechadas!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }

            }
        });
        thread.start();


    }

    public void iniciarComponentes() {
        searchView = findViewById(R.id.materialSearchView);
        rvEmpresasAbertas = findViewById(R.id.rvAbertos);
        rvEmpresasFechadas = findViewById(R.id.rvFechados);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_tela_inicial, menu);

        //Configurar botao de pesquisa
        MenuItem item = menu.findItem(R.id.menuPesquisa);
        searchView.setMenuItem(item);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuSair :
                deslogarUsuario();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario() {
        // TODO - Deslogar
    }

}
