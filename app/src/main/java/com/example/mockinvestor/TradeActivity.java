package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TradeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        getSupportActionBar().setTitle("Trade");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                startActivity(new Intent(TradeActivity.this, MainActivity.class));
            }
        });


    }
}
