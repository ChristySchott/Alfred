package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class MinhasInfosActivity extends AppCompatActivity {

    TextInputLayout txMinhasInfosNome, txMinhasInfosSobrenome, txMinhasInfosDataNascimento, txMinhasInfosTelefone, txMinhasInfosArea, txMinhasInfosCidade, txMinhasInfosBairro, txMinhasInfosRua, txMinhasInfosNumeroRua, txMinhasInfosComplemento;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_infos);

        // Configuração inicial dos componentes
        iniciarComponentes();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Minhas Infos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void iniciarComponentes() {
        txMinhasInfosNome = findViewById(R.id.txMinhasInfosNome);
        txMinhasInfosSobrenome = findViewById(R.id.txMinhasInfosSobrenome);
        txMinhasInfosDataNascimento = findViewById(R.id.txMinhasInfosDataNascimento);
        txMinhasInfosTelefone = findViewById(R.id.txMinhasInfosTelefone);
        txMinhasInfosArea = findViewById(R.id.txMinhasInfosArea);
        txMinhasInfosCidade = findViewById(R.id.txMinhasInfosCidade);
        txMinhasInfosBairro = findViewById(R.id.txMinhasInfosBairro);
        txMinhasInfosRua = findViewById(R.id.txMinhasInfosRua);
        txMinhasInfosNumeroRua = findViewById(R.id.txMinhasInfosNumeroRua);
        txMinhasInfosComplemento = findViewById(R.id.txMinhasInfosComplemento);
        btnMinhaContaCancelar = findViewById(R.id.btnMinhaContaCancelar);
        btnMinhaContaSalvar = findViewById(R.id.btnMinhaContaSalvar);
    }
}
