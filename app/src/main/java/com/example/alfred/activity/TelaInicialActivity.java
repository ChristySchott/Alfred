package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;

import adapter.AdapterEmpresas;
import modelDominio.Empresa;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class TelaInicialActivity extends AppCompatActivity {

    // private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView rvEmpresasAbertas, rvEmpresasFechadas;
    private List<Empresa> listaEmpresasAbertas =  new ArrayList<>();
    private List<Empresa> listaEmpresasFechadas =  new ArrayList<>();
    AdapterEmpresas adapterEmpresas;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_inicial);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        /* DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController); */

        // Iniciando os componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

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

                listaEmpresasFechadas = ccon.empresasAbertasLista();
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

    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaAberta = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasAbertas.get(position);
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            it.putExtra("empresa", minhaEmpresa);
            startActivity(it);
        }
    };

    AdapterEmpresas.EmpresaOnClickListener trataCliqueEmpresaFechada = new AdapterEmpresas.EmpresaOnClickListener() {
        @Override
        public void onClickEmpresa(View view, int position) {
            Empresa minhaEmpresa = listaEmpresasFechadas.get(position);
            Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
            it.putExtra("empresa", minhaEmpresa);
            startActivity(it);
        }
    };

    public void iniciarComponentes() {
        rvEmpresasAbertas = findViewById(R.id.rvAbertos);
        rvEmpresasFechadas = findViewById(R.id.rvFechados);
    }


    /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

     */
}
