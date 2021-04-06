package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class MinhaContaActivity extends AppCompatActivity {

    TextInputLayout txSenhaATUAL, txNovaSenha, txConfirmarNovaSenha;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        // Configuração inicial dos componentes
        iniciarComponentes();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Minha Conta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void iniciarComponentes() {
        txSenhaATUAL = findViewById(R.id.txSenhaAtual);
        txNovaSenha = findViewById(R.id.txNovaSenha);
        txConfirmarNovaSenha = findViewById(R.id.txConfirmarNovaSenha);
        btnMinhaContaCancelar = findViewById(R.id.btnMinhaContaCancelar);
        btnMinhaContaSalvar = findViewById(R.id.btnMinhaContaSalvar);
    }
}
