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
import com.example.alfred.modelDominio.Cliente;
import com.example.alfred.modelDominio.Usuario;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout txMainEmail, txMainSenha;
    Button btnMainEntrar, btnNaoPossuiConta, btnEsqueceuSenha;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração inicial dos components
        iniciarComponentes();


        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Cria o conexão controller, mas conecta somente 1 vez no Servidor durante toda a aplicacao.
        Thread t = new Thread(){
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
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        ConexaoController ccont = new ConexaoController(informacoesApp);

                        String email = txMainEmail.getEditText().getText().toString();
                        String senha = txMainSenha.getEditText().getText().toString();

                        if (email.equals("") || !Usuario.validaEmail(email)) {
                            txMainEmail.setError("Informe o e-mail");
                            txMainEmail.requestFocus();
                        } else if (txMainSenha.getEditText().getText().toString().equals("")) {
                            txMainSenha.setError("Informe a senha");
                            txMainSenha.requestFocus();
                        } else {

                            if (!email.equals("") && !senha.equals("")) {
                                Cliente clienteUsuario = new Cliente(email, senha);

                                Cliente clienteSelecionado = ccont.efetuarLogin(clienteUsuario);

                                Intent it = new Intent(MainActivity.this, TelaInicialActivity.class);
                                it.putExtra("cliente", clienteSelecionado);
                                startActivity(it);

                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Usuário ou senha inválida!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }

                    }
                };
                thread.start();

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
