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

public class CadastroActivity extends AppCompatActivity {

    TextInputEditText txCadastrarEmail, txCadastrarSenha, txCadastrarConfirmarSenha;
    Button btnCadastrar, btnPossuiConta;
    InformacoesApp informacoesApp;
    Cliente clienteCadastrar, clienteSelecionado;
    Usuario clienteUsuario, usuarioCriado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        // Configuração inicial dos componentes
        iniciarComponentes();

       // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = txCadastrarEmail.getText().toString();
                String senha = txCadastrarSenha.getText().toString();
                String confirmarSenha = txCadastrarConfirmarSenha.getText().toString();

                if (email.equals("") || !Usuario.validaEmail(email)) {
                    txCadastrarEmail.setError("Informe o e-mail");
                    txCadastrarEmail.requestFocus();
                } else if (senha.equals("")) {
                    txCadastrarSenha.setError("Informe a senha");
                    txCadastrarSenha.requestFocus();
                } else if (confirmarSenha.equals("")) {
                    txCadastrarConfirmarSenha.setError("Informe a confirmação de senha");
                    txCadastrarConfirmarSenha.requestFocus();
                } else {

                    if (!confirmarSenha.equals(senha)) {
                        txCadastrarSenha.setError("As senhas não coincidem");
                        txCadastrarConfirmarSenha.setError("As senhas não coincidem");
                        txCadastrarConfirmarSenha.requestFocus();
                    } else {

                        clienteUsuario = new Usuario(email, senha);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                ConexaoController ccont = new ConexaoController(informacoesApp);

                                boolean ok = ccont.usuarioInserir(clienteUsuario);

                                if (ok) {
                                        usuarioCriado = ccont.buscarUsuario(clienteUsuario);

                                        if (usuarioCriado != null) {
                                            clienteCadastrar = new Cliente(usuarioCriado.getCodUsuario());
                                            boolean clienteInserido = ccont.clienteInserir(clienteCadastrar);

                                            if (clienteInserido) {
                                                clienteSelecionado = ccont.efetuarLogin(clienteCadastrar);

                                                Intent it = new Intent(CadastroActivity.this, MainActivity.class);
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
                                                    Toast.makeText(CadastroActivity.this, "Erro!", Toast.LENGTH_SHORT).show();
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

    public void limpaCampos() {
        txCadastrarEmail.setText("");
        txCadastrarSenha.setText("");
        txCadastrarConfirmarSenha.setText("");
    }
}
