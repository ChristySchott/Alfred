package com.example.alfred.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;
import com.example.alfred.utils.Mascara;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Date;

import modelDominio.Cidade;
import modelDominio.Cliente;
import modelDominio.Estado;
import modelDominio.Usuario;

public class MinhasInfosActivity extends AppCompatActivity {

    TextInputEditText txMinhasInfosNome, txMinhasInfosSobrenome, txMinhasInfosDataNascimento, txMinhasInfosTelefone, txMinhasInfosArea, txMinhasInfosBairro, txMinhasInfosRua, txMinhasInfosNumeroRua, txMinhasInfosComplemento;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;
    InformacoesApp informacoesApp;
    Spinner spinnerCidade, spinnerEstado;
    Cliente cliente;
    Usuario usuario;
    Cidade cidadeSelecionada;
    Estado estadoSelecionado;
    ArrayList<Estado> listaEstados;
    ArrayList<Cidade> listaCidades;
    ArrayAdapter adapterEstados, adapterCidades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_infos);

        // Configuração inicial dos componentes
        iniciarComponentes();

        //Configurações Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Minhas Infos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        // Adiciona máscara no campo data
        txMinhasInfosDataNascimento.addTextChangedListener(Mascara.insert("##/##/####", txMinhasInfosDataNascimento));

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        Thread t = new Thread() {
            @Override
            public void run() {
                ConexaoController ccont = new ConexaoController(informacoesApp);
                listaEstados = ccont.listaEstados();
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        adapterEstados = new ArrayAdapter(informacoesApp, android.R.layout.simple_spinner_item, listaEstados);
                        spinnerEstado.setAdapter(adapterEstados);
                    }
                });


            }
        };
        t.start();

        AdapterView.OnItemSelectedListener escolheEstado = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                estadoSelecionado = (Estado) spinnerEstado.getSelectedItem();

                Thread t = new Thread() {
                    @Override
                    public void run() {
                        ConexaoController ccont = new ConexaoController(informacoesApp);
                        listaCidades = ccont.listaCidadesEstado(estadoSelecionado);

                        runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                adapterCidades = new ArrayAdapter(informacoesApp, android.R.layout.simple_spinner_item, listaCidades);
                                spinnerCidade.setAdapter(adapterCidades);
                            }
                        });


                    }
                };
                t.start();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerEstado.setOnItemSelectedListener(escolheEstado);

        AdapterView.OnItemSelectedListener escolheCidade = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cidadeSelecionada = (Cidade) spinnerCidade.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        spinnerCidade.setOnItemSelectedListener(escolheCidade);


        btnMinhaContaCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MinhasInfosActivity.this, TelaInicialActivity.class);
                startActivity(it);
            }
        });

        btnMinhaContaSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Informações do usuário logado
                final int codUsuario = informacoesApp.cliente.getCodUsuario();
                final String emailUsuario = informacoesApp.cliente.getEmailUsuario();
                final String senhaUsuario = informacoesApp.cliente.getSenhaUsuario();
                final int codCliente = informacoesApp.cliente.getCodCliente();

                if (informacoesApp.cliente.getNomeCliente() != null &&  !informacoesApp.cliente.getNomeCliente().equals("")) {
                    final String bairroUsuario = informacoesApp.cliente.getBairroUsuario();
                    final String cidadeUsuario = informacoesApp.cliente.getCidadeUsuario().getNomeCidade();
                    final String estadoUsuario = informacoesApp.cliente.getEstadoUsuario().getNomeEstado();
                    final String complementoUsuario = informacoesApp.cliente.getComplementoUsuario();

                    final String nomeCliente = informacoesApp.cliente.getNomeCliente();
                    final String sobrenomeCliente = informacoesApp.cliente.getSobrenomeCliente();
                    final String dataNascimentoClienteString = informacoesApp.cliente.getDataNascimentoClienteString();
                    final String telefoneCliente = String.valueOf(informacoesApp.cliente.getTelefoneCliente());

                    txMinhasInfosBairro.setText(bairroUsuario);
                    txMinhasInfosNome.setText(cidadeUsuario);
                    txMinhasInfosNome.setText(estadoUsuario);
                    txMinhasInfosNome.setText(telefoneCliente);
                    txMinhasInfosNome.setText(complementoUsuario);
                    txMinhasInfosNome.setText(nomeCliente);
                    txMinhasInfosNome.setText(sobrenomeCliente);
                    txMinhasInfosNome.setText(dataNascimentoClienteString);
                    txMinhasInfosNome.setText(telefoneCliente);
                }

                String nome = txMinhasInfosNome.getText().toString();
                String sobrenome = txMinhasInfosSobrenome.getText().toString();
                String dataNascimento = txMinhasInfosDataNascimento.getText().toString();
                String telefone = txMinhasInfosTelefone.getText().toString();
                String area = txMinhasInfosArea.getText().toString();
                String nomeCidade = spinnerCidade.getSelectedItem().toString();
                String nomeEstado = spinnerEstado.getSelectedItem().toString();
                String bairro = txMinhasInfosBairro.getText().toString();
                String rua = txMinhasInfosRua.getText().toString();
                String numeroRua = txMinhasInfosNumeroRua.getText().toString();
                String complemento = txMinhasInfosComplemento.getText().toString();

                if (nome.equals("")) {
                    txMinhasInfosNome.setError("Informe o nome");
                    txMinhasInfosNome.requestFocus();
                } else if (sobrenome.equals("")) {
                    txMinhasInfosSobrenome.setError("Informe o sobrenome");
                    txMinhasInfosSobrenome.requestFocus();
                } else if (dataNascimento.equals("")) {
                    txMinhasInfosDataNascimento.setError("Informe a data de nascimento");
                    txMinhasInfosDataNascimento.requestFocus();
                } else if (telefone.equals("")) {
                    txMinhasInfosTelefone.setError("Informe o telefone");
                    txMinhasInfosTelefone.requestFocus();
                } else if (area.equals("")) {
                    txMinhasInfosArea.setError("Informe a área");
                    txMinhasInfosArea.requestFocus();
                } else if (nomeCidade.equals("")) {
                    Toast.makeText(informacoesApp, "Informe a cidade", Toast.LENGTH_SHORT).show();
                } else if (nomeEstado.equals("")) {
                    Toast.makeText(informacoesApp, "Informe o estado", Toast.LENGTH_SHORT).show();
                } else if (bairro.equals("")) {
                    txMinhasInfosBairro.setError("Informe o bairro");
                    txMinhasInfosBairro.requestFocus();
                } else if (rua.equals("")) {
                    txMinhasInfosRua.setError("Informe a rua");
                    txMinhasInfosRua.requestFocus();
                } else if (numeroRua.equals("")) {
                    txMinhasInfosNumeroRua.setError("Informe o número da rua");
                    txMinhasInfosNumeroRua.requestFocus();
                } else {
                    String complementoFinal = complemento.equals("") ? complemento : null;
                    usuario = new Usuario(codUsuario, emailUsuario, senhaUsuario, cidadeSelecionada, estadoSelecionado, rua, bairro, complementoFinal, Integer.parseInt(numeroRua));
                    cliente = new Cliente(codCliente, nome, sobrenome, new Date(dataNascimento), Integer.parseInt(area), Integer.parseInt(telefone));

                    Thread thread = new Thread() {
                        @Override
                        public void run() {

                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            boolean okUsuario = ccont.usuarioAlterar(usuario);
                            boolean okCliente = ccont.clienteAlterar(cliente);

                            if (okUsuario && okCliente) {
                                informacoesApp.cliente = cliente;
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent it = new Intent(MinhasInfosActivity.this, TelaInicialActivity.class);
                                        startActivity(it);
                                    }
                                });
                            } else {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MinhasInfosActivity.this, "Erro ao atualizar o usuário!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    };
                    thread.start();
                }

            }
        });
    }

    public void iniciarComponentes() {
        txMinhasInfosNome = findViewById(R.id.txMinhasInfosNome);
        txMinhasInfosSobrenome = findViewById(R.id.txMinhasInfosSobrenome);
        txMinhasInfosDataNascimento = findViewById(R.id.txMinhasInfosDataNascimento);
        txMinhasInfosTelefone = findViewById(R.id.txMinhasInfosTelefone);
        txMinhasInfosArea = findViewById(R.id.txMinhasInfosArea);
        txMinhasInfosBairro = findViewById(R.id.txMinhasInfosBairro);
        txMinhasInfosRua = findViewById(R.id.txMinhasInfosRua);
        txMinhasInfosNumeroRua = findViewById(R.id.txMinhasInfosNumeroRua);
        txMinhasInfosComplemento = findViewById(R.id.txMinhasInfosComplemento);
        btnMinhaContaCancelar = findViewById(R.id.btnMinhasInfosCancelar);
        btnMinhaContaSalvar = findViewById(R.id.btnMinhasInfosSalvar);
        spinnerCidade = findViewById(R.id.spinner_cidades);
        spinnerEstado = findViewById(R.id.spinner_estados);
    }

    // Sobrescrevemos a função ao clicar na seta para retornar
    @Override
    public void onBackPressed(){
        Intent it = new Intent(MinhasInfosActivity.this, TelaInicialActivity.class);
        startActivity(it);
        finish();
        return;
    }

}
