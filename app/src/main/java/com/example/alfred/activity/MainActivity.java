package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    TextInputLayout txMainEmail, txMainPassword;
    Button btnMainSignIn, btnDoNotHaveAccount, btnForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configuração inicial dos components
        initComponents();

        // Configuração da Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        getSupportActionBar().hide();

        btnMainSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txMainEmail.getEditText().getText().toString().equals("")){
                    txMainEmail.setError("Informe o e-mail");
                    txMainEmail.requestFocus();
                } else if (txMainPassword.getEditText().getText().toString().equals("")){
                    txMainPassword.setError("Informe a senha");
                    txMainPassword.requestFocus();
                } else {
                    String email = txMainEmail.getEditText().getText().toString();
                    String password = txMainPassword.getEditText().getText().toString();

                    if (!email.equals("") && !password.equals("")){
                        Intent it = new Intent(MainActivity.this, MenuActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnDoNotHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(it);
            }
        });

        btnForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, RecoverPasswordActivity.class);
                startActivity(it);
            }
        });
    }

    private void initComponents() {
        txMainEmail = findViewById(R.id.txMainEmail);
        txMainPassword = findViewById(R.id.txMainPassword);
        btnMainSignIn = findViewById(R.id.btnMainSignIn);
        btnDoNotHaveAccount = findViewById(R.id.btnDoNotHaveAccount);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
    }
}
