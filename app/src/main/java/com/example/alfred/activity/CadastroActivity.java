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
import modelDominio.Cliente;
import modelDominio.Usuario;
import com.google.android.material.textfield.TextInputLayout;

public class CadastroActivity extends AppCompatActivity {

    TextInputLayout txCadastrarEmail, txCadastrarSenha, txCadastrarConfirmarSenha;
    Button btnCadastrar, btnPossuiConta;
    InformacoesApp informacoesApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Configuração inicial dos componentes
        iniciarComponentes();

//        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String email = txCadastrarEmail.getEditText().getText().toString();
                String senha = txCadastrarSenha.getEditText().getText().toString();

                if (email.equals("") || !Usuario.validaEmail(email)) {
                    txCadastrarEmail.setError("Informe o e-mail");
                    txCadastrarEmail.requestFocus();
                } else if (senha.equals("")) {
                    txCadastrarSenha.setError("Informe a senha");
                    txCadastrarSenha.requestFocus();
                } else {

                    Thread thread = new Thread() {
                        @Override
                        public void run() {

                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            Cliente clienteUsuario = new Cliente(txCadastrarEmail.getEditText().getText().toString(), txCadastrarSenha.getEditText().getText().toString());
                            boolean ok = ccont.usuarioInserir(clienteUsuario);

                            if (ok) {
                                Usuario usuarioCriado = ccont.buscarUsuario(clienteUsuario);

                                Cliente clienteCadastrar = new Cliente(usuarioCriado.getCodUsuario());
                                boolean clienteInserido = ccont.clienteInserir(clienteCadastrar);

                                if (clienteInserido) {
                                    Cliente clienteSelecionado = ccont.efetuarLogin(clienteUsuario);

                                    Intent it = new Intent(CadastroActivity.this, TelaInicialActivity.class);
                                    it.putExtra("cliente", clienteSelecionado);
                                    startActivity(it);
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(CadastroActivity.this, "Usuário já Cadastrado!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(CadastroActivity.this, "Erro ao Cadastrar!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
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
