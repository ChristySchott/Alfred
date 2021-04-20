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
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import modelDominio.Usuario;

public class MinhaContaActivity extends AppCompatActivity {

    TextInputEditText txSenhaAtual, txNovaSenha, txConfirmarNovaSenha;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;
    InformacoesApp informacoesApp;

    Usuario usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minha_conta);

        // Configuração inicial dos componentes
        iniciarComponentes();

        limpaCampos();

        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Minha Conta");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnMinhaContaCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpaCampos();
                Intent it = new Intent(MinhaContaActivity.this, TelaInicialActivity.class);
                startActivity(it);
            }
        });

        btnMinhaContaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String senhaAtual = txSenhaAtual.getText().toString();
                String senhaNova = txNovaSenha.getText().toString();
                String confirmarSenha = txConfirmarNovaSenha.getText().toString();

                final int codUsuarioLogado = informacoesApp.cliente.getCodUsuario();
                final String emailUsuarioLogado = informacoesApp.cliente.getEmailUsuario();
                final String senhaUsuarioLogado = informacoesApp.cliente.getSenhaUsuario();


                if (senhaAtual.equals("")) {
                    txSenhaAtual.setError("Informe a senha atual");
                    txSenhaAtual.requestFocus();
                } else if (!senhaAtual.equals(senhaUsuarioLogado)) {
                    txSenhaAtual.setError("Senha incorreta");
                    txSenhaAtual.requestFocus();
                } else if (senhaNova.equals("")) {
                    txNovaSenha.setError("Informe a senha");
                    txNovaSenha.requestFocus();
                } else if (confirmarSenha.equals("")) {
                    txConfirmarNovaSenha.setError("Informe a confirmação de senha");
                    txConfirmarNovaSenha.requestFocus();
                } else {

                    if (!confirmarSenha.equals(senhaNova)) {
                        txNovaSenha.setError("As senhas não coincidem");
                        txConfirmarNovaSenha.setError("As senhas não coincidem");
                        txConfirmarNovaSenha.requestFocus();
                    } else {

                        usuario = new Usuario(codUsuarioLogado, emailUsuarioLogado, senhaNova);

                        Thread thread = new Thread() {
                            @Override
                            public void run() {

                                ConexaoController ccont = new ConexaoController(informacoesApp);

                                boolean ok = ccont.usuarioAlterar(usuario);

                                if (ok) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            limpaCampos();
                                            Toast.makeText(MinhaContaActivity.this, "Senha atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(MinhaContaActivity.this, "Erro ao atualizar a senha!", Toast.LENGTH_SHORT).show();
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
    }

    private void iniciarComponentes() {
        txSenhaAtual = findViewById(R.id.txSenhaAtual);
        txNovaSenha = findViewById(R.id.txNovaSenha);
        txConfirmarNovaSenha = findViewById(R.id.txConfirmarNovaSenha);
        btnMinhaContaCancelar = findViewById(R.id.btnMinhaContaCancelar);
        btnMinhaContaSalvar = findViewById(R.id.btnMinhaContaSalvar);
    }

    private void limpaCampos() {
        txSenhaAtual.setText("");
        txNovaSenha.setText("");
        txConfirmarNovaSenha.setText("");
    }
}
