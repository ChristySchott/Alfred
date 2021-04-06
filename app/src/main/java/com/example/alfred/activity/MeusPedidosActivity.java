package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;

public class MeusPedidosActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meus_pedidos);

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Meus Pedidos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
