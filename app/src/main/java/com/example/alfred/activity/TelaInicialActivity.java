package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import adapter.AdapterEmpresas;
import modelDominio.Empresa;

public class TelaInicialActivity extends AppCompatActivity {

    AdapterEmpresas adapterEmpresas;
    InformacoesApp informacoesApp;
    private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView rvEmpresasAbertas, rvEmpresasFechadas;

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

        //Cria referencia para toda a área do NavigationDrawer
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        //Cria referencia para a área de navegação
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Define configurações do NavigationDrawer
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_meus_pedidos, R.id.nav_minhas_infos, R.id.nav_minha_senha)
                .setDrawerLayout(drawer)
                .build();

        //Configura area que irá carregar os fragments
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //Configura menu superior de navegaçao
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);

        //Configura navegacao para o NavigationView
        NavigationUI.setupWithNavController(navigationView, navController);

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

    public void iniciarComponentes() {
        rvEmpresasAbertas = findViewById(R.id.rvAbertos);
        rvEmpresasFechadas = findViewById(R.id.rvFechados);
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

}
