package com.example.mockinvestor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StockInfoActivity extends AppCompatActivity {
    //display stock info and the option to buy or sell
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_info_layout);
        getSupportActionBar().setTitle("Stock Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Stock selectedStock = (Stock) getIntent().getSerializableExtra("stock");
        TextView company_name = findViewById(R.id.company_name);
        TextView price_per_share = findViewById(R.id.price_per_share);
        TextView market_cap = findViewById(R.id.market_cap);
        TextView previous_close = findViewById(R.id.previous_close);
        TextView open = findViewById(R.id.open);
        TextView volume = findViewById(R.id.volume);


    }
}
