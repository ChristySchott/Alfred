package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout txSignUpEmail, txSignUpPassword, txSignUpConfirmPassword;
    Button btnSignUp, btnHaveAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Configuração inicial dos componentes
        initComponents();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txSignUpEmail.getEditText().getText().toString().equals("")){
                    txSignUpEmail.setError("Informe o e-mail");
                    txSignUpEmail.requestFocus();
                } else if (txSignUpPassword.getEditText().getText().toString().equals("")){
                    txSignUpPassword.setError("Informe a senha");
                    txSignUpPassword.requestFocus();
                } else {
                    String email = txSignUpEmail.getEditText().getText().toString();
                    String password = txSignUpPassword.getEditText().getText().toString();

                    if (!email.equals("") && !password.equals("")){
                        Intent it = new Intent(SignUpActivity.this, MainActivity.class);
                        it.putExtra("email", email);
                        startActivity(it);
                    }
                }
            }
        });

        btnHaveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(SignUpActivity.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    public void initComponents() {
        txSignUpEmail = findViewById(R.id.txSignUpEmail);
        txSignUpPassword = findViewById(R.id.txSignUpPassword);
        txSignUpConfirmPassword = findViewById(R.id.txSignUpConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnHaveAccount = findViewById(R.id.btnHaveAccount);
    }
}
