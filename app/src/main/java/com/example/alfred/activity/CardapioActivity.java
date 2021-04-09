package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.listener.RecyclerItemClickListener;
import com.example.alfred.adapter.AdapterPratos;
import com.example.alfred.modelDominio.Prato;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class CardapioActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPratos;
    private List<Prato> pratoList =  new ArrayList<>();
    private InformacoesApp infosApp;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardapio);

        recyclerViewPratos = findViewById(R.id.rvPratos);

        infosApp = (InformacoesApp) getApplicationContext();

        // Listagem de Pratos
        this.criarPratos();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Cardápio");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Configurar adapter para o RecyclerView
        AdapterPratos adapterPratos = new AdapterPratos(pratoList);

        // Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewPratos.setLayoutManager(layoutManager);
        recyclerViewPratos.setHasFixedSize(true);
        recyclerViewPratos.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerViewPratos.setAdapter(adapterPratos);

        // Evento de Clique em um item da Recycler View
        recyclerViewPratos.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerViewPratos,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                // TODO - ADICIONAR AO CARRINHO
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

    // TODO - BUSCAR DO BANCO
    public void criarPratos() {
        Prato restaurant = new Prato("name", "description",  5.5, 1);
        pratoList.add(restaurant);
        Prato restaurant2 = new Prato("name", "description",  5.5, 1);
        pratoList.add(restaurant2);
        Prato restaurant3 = new Prato("name", "description",  5.5, 1);
        pratoList.add(restaurant3);
        Prato restaurant4 = new Prato("name", "description", 5.5, 1);
        pratoList.add(restaurant4);
    }
}
