package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class RecuperarSenhaActivity extends AppCompatActivity {

    TextInputLayout txRecuperarSenhaEmail;
    Button btnRecuperarSenha, btnPossuiContaRecuperarSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        // Configuração inicial dos componentes
        iniciarComponentes();

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txRecuperarSenhaEmail.getEditText().getText().toString().equals("")){
                    txRecuperarSenhaEmail.setError("Informe o e-mail");
                    txRecuperarSenhaEmail.requestFocus();
                } else {
                    String email = txRecuperarSenhaEmail.getEditText().getText().toString();

                    if (!email.equals("")){
                        Intent it = new Intent(RecuperarSenhaActivity.this, MainActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnPossuiContaRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RecuperarSenhaActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    private void iniciarComponentes() {
        txRecuperarSenhaEmail = findViewById(R.id.txRecuperarSenhaEmail);
        btnRecuperarSenha = findViewById(R.id.btnRecuperarSenha);
        btnPossuiContaRecuperarSenha = findViewById(R.id.btnPossuiContaRecuperarSenha);
    }

}
