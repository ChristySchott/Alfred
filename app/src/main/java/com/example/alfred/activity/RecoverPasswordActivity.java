package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class RecoverPasswordActivity extends AppCompatActivity {

    TextInputLayout txRecoverPasswordEmail;
    Button btnRecoverPassword, btnHaveAccountRP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recover_password);

        // Configuração inicial dos componentes
        initComponents();

        // Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().hide();

        btnRecoverPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txRecoverPasswordEmail.getEditText().getText().toString().equals("")){
                    txRecoverPasswordEmail.setError("Informe o e-mail");
                    txRecoverPasswordEmail.requestFocus();
                } else {
                    String email = txRecoverPasswordEmail.getEditText().getText().toString();

                    if (!email.equals("")){
                        Intent it = new Intent(RecoverPasswordActivity.this, MainActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnHaveAccountRP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(RecoverPasswordActivity.this, MainActivity.class);
                startActivity(it);
            }
        });

    }

    private void initComponents() {
        txRecoverPasswordEmail = findViewById(R.id.txRecoverPasswordEmail);
        btnRecoverPassword = findViewById(R.id.btnRecoverPassword);
        btnHaveAccountRP = findViewById(R.id.btnHaveAccountRP);
    }

}
