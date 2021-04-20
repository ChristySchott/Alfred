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
import com.google.android.material.textfield.TextInputEditText;

import modelDominio.Cliente;
import modelDominio.Usuario;

public class MainActivity extends AppCompatActivity {

    TextInputEditText txMainEmail, txMainSenha;
    Button btnMainEntrar, btnNaoPossuiConta, btnEsqueceuSenha;
    InformacoesApp informacoesApp;
    Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração inicial dos components
        iniciarComponentes();

        limpaCampos();

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Cria o conexão controller, mas conecta somente 1 vez no Servidor durante toda a aplicacao.
        Thread t = new Thread() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(informacoesApp);
                ccont.Conectar();
            }
        };
        t.start();


        btnMainEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txMainEmail.getText().toString();
                String senha = txMainSenha.getText().toString();

                if (email.equals("") || !Usuario.validaEmail(email)) {
                    txMainEmail.setError("Informe o e-mail");
                    txMainEmail.requestFocus();
                } else if (txMainSenha.getText().toString().equals("")) {
                    txMainSenha.setError("Informe a senha");
                    txMainSenha.requestFocus();
                } else {

                    if (!email.equals("") && !senha.equals("")) {
                        cliente = new Cliente(email, senha);
                        Thread thread = new Thread() {
                            @Override
                            public void run() {
                                ConexaoController ccont = new ConexaoController(informacoesApp);
                                cliente = ccont.efetuarLogin(cliente);

                                // TODO - Unificar cliente em InformacoesApp
                                if (cliente != null) {
                                    informacoesApp.cliente = cliente;

                                    if (informacoesApp.cliente.getNomeCliente() != null &&  !informacoesApp.cliente.getNomeCliente().equals("")) {
                                        Intent it = new Intent(MainActivity.this, TelaInicialActivity.class);
                                        startActivity(it);
                                    } else {
                                        Intent it = new Intent(MainActivity.this, MinhasInfosActivity.class);
                                        startActivity(it);
                                    }

                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MainActivity.this, "Usuário ou senha inválida!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        };
                        thread.start();
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

    public void limpaCampos() {
        txMainEmail.setText("");
        txMainSenha.setText("");
    }

}
