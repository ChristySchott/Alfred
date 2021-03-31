package com.example.alfred.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.RadioButton;

import com.example.alfred.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;

public class CartActivity extends AppCompatActivity {

    RadioButton rbCartCash, rbCartCard;
    TextInputLayout txCartObservation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Configuração inicial dos components
        initComponents();

        // Configuração da Toolbar
        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Carrinho");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_cart, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void initComponents() {
        rbCartCash = findViewById(R.id.rbCartCash);
        rbCartCard = findViewById(R.id.rbCartCard);
        txCartObservation = findViewById(R.id.txCartObservation);
    }

}
