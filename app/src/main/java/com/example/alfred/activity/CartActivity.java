package com.example.alfred.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;

import com.example.alfred.R;
import com.google.android.material.textfield.TextInputLayout;

public class CartActivity extends AppCompatActivity {

    RadioButton rbCartCash, rbCartCard;
    TextInputLayout txCartObservation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        rbCartCash = findViewById(R.id.rbCartCash);
        rbCartCard = findViewById(R.id.rbCartCard);

        txCartObservation = findViewById(R.id.txCartObservation);
    }
}
