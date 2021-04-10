package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import modelDominio.Endereco;

public class MinhasInfosActivity extends AppCompatActivity {

    TextInputEditText txMinhasInfosNome, txMinhasInfosSobrenome, txMinhasInfosDataNascimento, txMinhasInfosTelefone, txMinhasInfosArea, txMinhasInfosCidade, txMinhasInfosBairro, txMinhasInfosRua, txMinhasInfosNumeroRua, txMinhasInfosComplemento, txMinhasInfosEstado;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;
    InformacoesApp informacoesApp;
    Endereco endereco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_infos);

        // Configuração inicial dos componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Minhas Infos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnMinhaContaCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MinhasInfosActivity.this, TelaInicialActivity.class);
                startActivity(it);
            }
        });

        btnMinhaContaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = txMinhasInfosNome.getText().toString();
                String sobrenome = txMinhasInfosSobrenome.getText().toString();
                String dataNascimento = txMinhasInfosDataNascimento.getText().toString();
                String telefone = txMinhasInfosTelefone.getText().toString();
                String area = txMinhasInfosArea.getText().toString();
                String cidade = txMinhasInfosCidade.getText().toString();
                String estado = txMinhasInfosEstado.getText().toString();
                String bairro = txMinhasInfosBairro.getText().toString();
                String rua = txMinhasInfosRua.getText().toString();
                String numeroRua = txMinhasInfosNumeroRua.getText().toString();
                String complemento = txMinhasInfosComplemento.getText().toString();

                if (nome.equals("")) {
                    txMinhasInfosNome.setError("Informe o nome");
                    txMinhasInfosNome.requestFocus();
                } else if (sobrenome.equals("")) {
                    txMinhasInfosSobrenome.setError("Informe o sobrenome");
                    txMinhasInfosSobrenome.requestFocus();
                } else if (dataNascimento.equals("")) {
                    txMinhasInfosDataNascimento.setError("Informe a data de nascimento");
                    txMinhasInfosDataNascimento.requestFocus();
                } else if (telefone.equals("")) {
                    txMinhasInfosTelefone.setError("Informe o telefone");
                    txMinhasInfosTelefone.requestFocus();
                } else if (area.equals("")) {
                    txMinhasInfosArea.setError("Informe a área");
                    txMinhasInfosArea.requestFocus();
                } else if (cidade.equals("")) {
                    txMinhasInfosCidade.setError("Informe a cidade");
                    txMinhasInfosCidade.requestFocus();
                } else if (estado.equals("")) {
                    txMinhasInfosEstado.setError("Informe o estado");
                    txMinhasInfosEstado.requestFocus();
                } else if (bairro.equals("")) {
                    txMinhasInfosBairro.setError("Informe o bairro");
                    txMinhasInfosBairro.requestFocus();
                } else if (rua.equals("")) {
                    txMinhasInfosRua.setError("Informe a rua");
                    txMinhasInfosRua.requestFocus();
                } else if (numeroRua.equals("")) {
                    txMinhasInfosNumeroRua.setError("Informe o número da rua");
                    txMinhasInfosNumeroRua.requestFocus();
                } else {
                    endereco = new Endereco(cidade, estado, rua, bairro, complemento);

                    // TODO - Criar alterarCliente no controller
                    }

            }
        });
    }

    private void iniciarComponentes() {
        txMinhasInfosNome = findViewById(R.id.txMinhasInfosNome);
        txMinhasInfosSobrenome = findViewById(R.id.txMinhasInfosSobrenome);
        txMinhasInfosDataNascimento = findViewById(R.id.txMinhasInfosDataNascimento);
        txMinhasInfosTelefone = findViewById(R.id.txMinhasInfosTelefone);
        txMinhasInfosArea = findViewById(R.id.txMinhasInfosArea);
        txMinhasInfosCidade = findViewById(R.id.txMinhasInfosCidade);
        txMinhasInfosEstado = findViewById(R.id.txMinhasInfosEstado);
        txMinhasInfosBairro = findViewById(R.id.txMinhasInfosBairro);
        txMinhasInfosRua = findViewById(R.id.txMinhasInfosRua);
        txMinhasInfosNumeroRua = findViewById(R.id.txMinhasInfosNumeroRua);
        txMinhasInfosComplemento = findViewById(R.id.txMinhasInfosComplemento);
        btnMinhaContaCancelar = findViewById(R.id.btnMinhaContaCancelar);
        btnMinhaContaSalvar = findViewById(R.id.btnMinhaContaSalvar);
    }

}
