package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;
import com.google.android.material.textfield.TextInputLayout;

public class RecuperarSenhaActivity extends AppCompatActivity {

    TextInputLayout txRecuperarSenhaEmail;
    Button btnRecuperarSenha, btnPossuiContaRecuperarSenha;
    InformacoesApp informacoesApp;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_senha);

        // Configuração inicial dos componentes
        iniciarComponentes();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        btnRecuperarSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txRecuperarSenhaEmail.getEditText().getText().toString();
                if (email.equals("")) {
                    txRecuperarSenhaEmail.setError("Informe o e-mail");
                    txRecuperarSenhaEmail.requestFocus();
                } else {

                    Thread thread = new Thread() {
                        @Override
                        public void run() {
                            ConexaoController ccont = new ConexaoController(informacoesApp);
                            boolean ok = ccont.recuperarSenha(email);

                            if (ok) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RecuperarSenhaActivity.this, "Email para Recuperação de Senha enviado!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(RecuperarSenhaActivity.this, "Erro ao tentar recuperar a senha!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
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
