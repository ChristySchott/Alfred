package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class MyAccountActivity extends AppCompatActivity {

    TextInputLayout txCurrentPassword, txNewPassword, txConfirmNewPassword;
    Button btnMyAccountCancel, btnMyAccountSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        // Configuração inicial dos componentes
        initComponents();
    }

    private void initComponents() {
        txCurrentPassword = findViewById(R.id.txCurrentPassword);
        txNewPassword = findViewById(R.id.txNewPassword);
        txConfirmNewPassword = findViewById(R.id.txConfirmNewPassword);
        btnMyAccountCancel = findViewById(R.id.btnMyAccountCancel);
        btnMyAccountSave = findViewById(R.id.btnMyAccountSave);
    }
}
