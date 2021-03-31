package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class MyInfosActivity extends AppCompatActivity {

    TextInputLayout txMyInfosName, txMyInfosSurname, txMyInfosBirthday, txMyInfosPhone, txMyInfosArea, txMyInfosCity, txMyInfosNeighbordhood, txMyInfosStreet, txMyInfosStreetNumber, txMyInfosComplement;
    Button btnMyAccountCancel, btnMyAccountSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_infos);

        // Configuração inicial dos componentes
        initComponents();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Minhas Infos");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    private void initComponents() {
        txMyInfosName = findViewById(R.id.txMyInfosName);
        txMyInfosSurname = findViewById(R.id.txMyInfosSurname);
        txMyInfosBirthday = findViewById(R.id.txMyInfosBirthday);
        txMyInfosPhone = findViewById(R.id.txMyInfosPhone);
        txMyInfosArea = findViewById(R.id.txMyInfosArea);
        txMyInfosCity = findViewById(R.id.txMyInfosCity);
        txMyInfosNeighbordhood = findViewById(R.id.txMyInfosNeighbordhood);
        txMyInfosStreet = findViewById(R.id.txMyInfosStreet);
        txMyInfosStreetNumber = findViewById(R.id.txMyInfosStreetNumber);
        txMyInfosComplement = findViewById(R.id.txMyInfosComplement);
        btnMyAccountCancel = findViewById(R.id.btnMyAccountCancel);
        btnMyAccountSave = findViewById(R.id.btnMyAccountSave);
    }
}
