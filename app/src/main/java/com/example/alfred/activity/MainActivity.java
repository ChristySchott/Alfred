package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout txMainEmail, txMainSenha;
    Button btnMainEntrar, btnNaoPossuiConta, btnEsqueceuSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração inicial dos components
        iniciarComponentes();

        btnMainEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txMainEmail.getEditText().getText().toString().equals("")){
                    txMainEmail.setError("Informe o e-mail");
                    txMainEmail.requestFocus();
                } else if (txMainSenha.getEditText().getText().toString().equals("")){
                    txMainSenha.setError("Informe a senha");
                    txMainSenha.requestFocus();
                } else {
                    String email = txMainEmail.getEditText().getText().toString();
                    String password = txMainSenha.getEditText().getText().toString();

                    if (!email.equals("") && !password.equals("")){
                        Intent it = new Intent(MainActivity.this, TelaInicialActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnNaoPossuiConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, CadastroActivity.class);
                startActivity(it);
            }
        });

        btnEsqueceuSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, RecuperarSenhaActivity.class);
                startActivity(it);
            }
        });
    }

    private void iniciarComponentes() {
        txMainEmail = findViewById(R.id.txMainEmail);
        txMainSenha = findViewById(R.id.txMainSenha);
        btnMainEntrar = findViewById(R.id.btnMainEntrar);
        btnNaoPossuiConta = findViewById(R.id.btnNaoPossuiConta);
        btnEsqueceuSenha = findViewById(R.id.btnEsqueceuSenha);
    }
}
