package com.example.alfred.activity;

import android.util.Log;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alfred.InformacoesApp;
import com.example.alfred.R;
import com.example.alfred.controller.ConexaoController;
import com.example.alfred.utils.Mascara;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;

import modelDominio.Cidade;
import modelDominio.Cliente;
import modelDominio.Estado;
import modelDominio.Usuario;

public class MinhasInfosActivity extends AppCompatActivity {

    TextInputEditText txMinhasInfosNome, txMinhasInfosSobrenome, txMinhasInfosDataNascimento, txMinhasInfosTelefone, txMinhasInfosArea, txMinhasInfosBairro, txMinhasInfosRua, txMinhasInfosNumeroRua, txMinhasInfosComplemento;
    Button btnMinhaContaCancelar, btnMinhaContaSalvar;
    ImageView ivMinhasInfosFoto;
    MaterialButton btnMinhaContaFoto;
    InformacoesApp informacoesApp;
    Spinner spinnerCidade, spinnerEstado;
    Cliente cliente;
    Usuario usuario;
    Cidade cidadeSelecionada;
    Estado estadoSelecionado;
    ArrayList<Estado> listaEstados;
    ArrayList<Cidade> listaCidades;
    ArrayAdapter adapterEstados, adapterCidades;
    byte[] imagemSelecionada;

    // Resultado esperado na seleção da imagem
    int SELECIONA_IMAGEM = 200;

    private static final int REQUEST_GET_SINGLE_FILE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minhas_infos);

        // Contexto
        informacoesApp = (InformacoesApp) getApplicationContext();

        // Configuração inicial dos componentes
        iniciarComponentes();

        //Configurações Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbarMaterial);
        toolbar.setTitle("Minhas Infos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Adiciona máscara no campo data
        txMinhasInfosDataNascimento.addTextChangedListener(Mascara.insert("##/##/####", txMinhasInfosDataNascimento));

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

        btnMinhaContaFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selecionarImagem();
            }
        });


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

                Bitmap bitmap = ((BitmapDrawable) ivMinhasInfosFoto.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                byte[] imagem = stream.toByteArray();

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
                    usuario = new Usuario(codUsuario, emailUsuario, senhaUsuario, cidadeSelecionada, estadoSelecionado, rua, bairro, complemento, Integer.parseInt(numeroRua));
                    cliente = new Cliente(codCliente, nome, sobrenome, new Date(dataNascimento), Integer.parseInt(area), Integer.parseInt(telefone), imagem, informacoesApp.cliente.getSenhaUsuario(), informacoesApp.cliente.getEmailUsuario());
                    Thread thread = new Thread() {
                        @Override
                        public void run() {

                            ConexaoController ccont = new ConexaoController(informacoesApp);

                            boolean okUsuario = ccont.usuarioAlterar(usuario);
                            boolean okCliente = ccont.clienteAlterar(cliente);

                            if (okUsuario && okCliente) {
                                informacoesApp.cliente.setNomeCliente(cliente.getNomeCliente());
                                informacoesApp.cliente.setSobrenomeCliente(cliente.getSobrenomeCliente());
                                informacoesApp.cliente.setAreaCliente(cliente.getAreaCliente());
                                informacoesApp.cliente.setTelefoneCliente(cliente.getTelefoneCliente());
                                informacoesApp.cliente.setDataNascimentoCliente(cliente.getDataNascimentoCliente());
                                informacoesApp.cliente.setDataNascimentoCliente(cliente.getDataNascimentoCliente());
                                informacoesApp.cliente.setImagemCliente(cliente.getImagemCliente());

                                informacoesApp.cliente.setEmailUsuario(usuario.getEmailUsuario());
                                informacoesApp.cliente.setCidadeUsuario(usuario.getCidadeUsuario());
                                informacoesApp.cliente.setEstadoUsuario(usuario.getEstadoUsuario());
                                informacoesApp.cliente.setRuaUsuario(usuario.getRuaUsuario());
                                informacoesApp.cliente.setBairroUsuario(usuario.getBairroUsuario());
                                informacoesApp.cliente.setNumeroUsuario(usuario.getNumeroUsuario());
                                informacoesApp.cliente.setComplementoUsuario(usuario.getComplementoUsuario());
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent it = new Intent(getApplicationContext(), TelaInicialActivity.class);
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
        btnMinhaContaFoto = findViewById(R.id.btnMinhasInfosFoto);
        spinnerCidade = findViewById(R.id.spinner_cidades);
        spinnerEstado = findViewById(R.id.spinner_estados);
        ivMinhasInfosFoto = findViewById(R.id.ivMinhasInfosFoto);

        Cliente cliente = informacoesApp.cliente;

        if (cliente != null) {
            if (cliente.getImagemCliente() != null) {
                Bitmap bmp = BitmapFactory.decodeByteArray(cliente.getImagemCliente(), 0, cliente.getImagemCliente().length);
                ivMinhasInfosFoto.setImageBitmap(bmp);
            }
            txMinhasInfosNome.setText(cliente.getNomeCliente());
            txMinhasInfosSobrenome.setText(cliente.getSobrenomeCliente());
            txMinhasInfosDataNascimento.setText(cliente.getDataNascimentoClienteString());
            txMinhasInfosTelefone.setText(cliente.getTelefoneStringCliente());
            txMinhasInfosArea.setText(cliente.getAreaStringCliente());
            txMinhasInfosBairro.setText(cliente.getBairroUsuario());
            txMinhasInfosRua.setText(cliente.getRuaUsuario());
            txMinhasInfosNumeroRua.setText(cliente.getNumeroUsuarioToString());
            txMinhasInfosComplemento.setText(cliente.getComplementoUsuario());
        }
    }

    void selecionarImagem() {

        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(i, "Seleciona Foto"), REQUEST_GET_SINGLE_FILE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_GET_SINGLE_FILE) {
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // TODO - Salvar imagem em algum estado local
                    ivMinhasInfosFoto.setImageURI(selectedImageUri);
                }
            }
        }
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
