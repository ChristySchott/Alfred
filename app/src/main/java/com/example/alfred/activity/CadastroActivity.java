package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroActivity extends AppCompatActivity {

    TextInputLayout txCadastrarEmail, txCadastrarSenha, txCadastrarConfirmarSenha;
    Button btnCadastrar, btnPossuiConta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Configuração inicial dos componentes
        iniciarComponentes();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txCadastrarEmail.getEditText().getText().toString().equals("")){
                    txCadastrarEmail.setError("Informe o e-mail");
                    txCadastrarEmail.requestFocus();
                } else if (txCadastrarSenha.getEditText().getText().toString().equals("")){
                    txCadastrarSenha.setError("Informe a senha");
                    txCadastrarSenha.requestFocus();
                } else {
                    String email = txCadastrarEmail.getEditText().getText().toString();
                    String password = txCadastrarSenha.getEditText().getText().toString();

                    if (!email.equals("") && !password.equals("")){
                        Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnPossuiConta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    public void iniciarComponentes() {
        txCadastrarEmail = findViewById(R.id.txCadastroEmail);
        txCadastrarSenha = findViewById(R.id.txCadastroSenha);
        txCadastrarConfirmarSenha = findViewById(R.id.txCadastroConfirmarSenha);
        btnCadastrar = findViewById(R.id.btnCadastro);
        btnPossuiConta = findViewById(R.id.btnPossuiConta);
    }
}
