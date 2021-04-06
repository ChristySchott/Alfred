package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RadioButton;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class CarrinhoActivity extends AppCompatActivity {

    RadioButton rbCarrinhoDinheiro, rbCarrinhoCartao;
    TextInputLayout txCarrinhoObservacao;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        // Configuração inicial dos components
        iniciarComponentes();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Carrinho");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void iniciarComponentes() {
        rbCarrinhoDinheiro = findViewById(R.id.rbCarrinhoDinheiro);
        rbCarrinhoCartao = findViewById(R.id.rbCarrinhoCartao);
        txCarrinhoObservacao = findViewById(R.id.txCarrinhoObservacao);
    }

}
