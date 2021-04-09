package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.alfred.R;
import com.example.alfred.listener.RecyclerItemClickListener;
import adapter.AdapterEmpresas;
import modelDominio.Empresa;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.AdapterView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class TelaInicialActivity extends AppCompatActivity {

    // private AppBarConfiguration mAppBarConfiguration;
    private RecyclerView recyclerViewEmpresasAbertas;
    private List<Empresa> listaEmpresas =  new ArrayList<>();

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

        recyclerViewEmpresasAbertas = findViewById(R.id.rvAbertos);


        // Configurar adapter para o RecyclerView
        AdapterEmpresas adapterEmpresas = new AdapterEmpresas(listaEmpresas);

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewEmpresasAbertas.setLayoutManager(layoutManager);
        recyclerViewEmpresasAbertas.setHasFixedSize(true);
        recyclerViewEmpresasAbertas.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewEmpresasAbertas.setAdapter(adapterEmpresas);

        // Evento de Clique em um item da Recycler View
        recyclerViewEmpresasAbertas.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewEmpresasAbertas,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Empresa myEmpresa = listaEmpresas.get(position);

                                Intent it = new Intent(TelaInicialActivity.this, CardapioActivity.class);
                                it.putExtra("restaurant", myEmpresa);
                                startActivity(it);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );
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
