package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.adapter.AdapterCategorias;
import com.example.alfred.adapter.AdapterEmpresas;
import com.example.alfred.controller.ConexaoController;
import com.example.alfred.utils.PaddingItemDecoration;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import modelDominio.Categoria;
import modelDominio.Empresa;

public class TelaInicialActivity extends AppCompatActivity {

    AdapterEmpresas adapterEmpresas;
    AdapterCategorias adapterCategorias;
    InformacoesApp informacoesApp;
    RecyclerView rvEmpresasAbertas, rvEmpresasFechadas, rvCategorias;
    MaterialSearchView searchView;
    String filtroEmpresasNome = "", filtroCodCategoria = "";
    TextView txNavHeaderNomeUsuario;
    DrawerLayout drawerLayout;

    private List<Empresa> listaEmpresasAbertas = new ArrayList<>();
    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaAberta = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasAbertas.get(position);
            informacoesApp.empresa = minhaEmpresa;
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            startActivity(it);
        }
    };
    private List<Categoria> listaCategoria = new ArrayList<>();
    private List<Empresa> listaEmpresasFechadas = new ArrayList<>();
    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaFechada = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasFechadas.get(position);
            informacoesApp.empresa = minhaEmpresa;
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            startActivity(it);
        }
    };

    AdapterCategorias.CategoriaOnClickListener trateCliqueCategoria = new AdapterCategorias.CategoriaOnClickListener() {
        @Override
        public void onClickCategoria(View view, int position) {
            Categoria minhaCategoria = listaCategoria.get(position);
            String codCategoria = String.valueOf(minhaCategoria.getCodCategoria());

            if (codCategoria == filtroCodCategoria) {
                filtroCodCategoria = "";
            } else {
                filtroCodCategoria = codCategoria;
            }

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {

                    ConexaoController ccon = new ConexaoController(informacoesApp);
                    listaEmpresasAbertas = ccon.empresasAbertasLista(filtroEmpresasNome, filtroCodCategoria);
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
                    }

                    listaEmpresasFechadas = ccon.empresasFechadasLista(filtroEmpresasNome, filtroCodCategoria);
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
                    }

                }
            });
            thread.start();

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

        final String nome = informacoesApp.cliente.getNomeCliente();
        String sobrenome = informacoesApp.cliente.getSobrenomeCliente();

        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        toolbar.setTitle(nome + " " + sobrenome);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        txNavHeaderNomeUsuario = headerView.findViewById(R.id.txNavHeaderNomeUsuario);
        txNavHeaderNomeUsuario.setText(nome + " " + sobrenome);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_meus_pedidos) {
                    Intent it = new Intent(TelaInicialActivity.this, MeusPedidosActivity.class);
                    startActivity(it);
                } else if (id == R.id.nav_minha_senha) {
                    Intent it = new Intent(TelaInicialActivity.this, MinhaContaActivity.class);
                    startActivity(it);
                } else if (id == R.id.nav_minhas_infos) {
                    Intent it = new Intent(TelaInicialActivity.this, MinhasInfosActivity.class);
                    startActivity(it);
                }

                return true;
            }
        });

        //Configuração do search view
        searchView.setHint("pesquisar empresas");
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filtroEmpresasNome = newText;
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ConexaoController ccon = new ConexaoController(informacoesApp);
                        listaEmpresasAbertas = ccon.empresasAbertasLista(filtroEmpresasNome, filtroCodCategoria);
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
                        }

                        listaEmpresasFechadas = ccon.empresasFechadasLista(filtroEmpresasNome, filtroCodCategoria);
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
                        }

                    }
                });
                thread.start();
                return true;
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                ConexaoController ccon = new ConexaoController(informacoesApp);
                listaEmpresasAbertas = ccon.empresasAbertasLista(filtroEmpresasNome, filtroCodCategoria);
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

                listaEmpresasFechadas = ccon.empresasFechadasLista(filtroEmpresasNome, filtroCodCategoria);
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

                listaCategoria = ccon.categoriaLista();
                if (listaCategoria != null) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapterCategorias = new AdapterCategorias(listaCategoria, trateCliqueCategoria);
                            rvCategorias.setLayoutManager(new LinearLayoutManager(informacoesApp, LinearLayoutManager.HORIZONTAL, false));
                            rvCategorias.setHasFixedSize(true);
                            rvCategorias.addItemDecoration(new PaddingItemDecoration(50));
                            rvCategorias.setAdapter(adapterCategorias);
                        }
                    });
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(informacoesApp, "ATENÇÃO: Não foi possível obter a lista das categorias", Toast.LENGTH_SHORT).show();
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
        rvCategorias = findViewById(R.id.rvCategorias);
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

        switch (item.getItemId()) {
            case R.id.menuSair:
                deslogarUsuario();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario() {
        // TODO - Deslogar
    }

}
