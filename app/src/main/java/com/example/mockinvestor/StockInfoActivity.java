package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;

import java.util.ArrayList;

public class StockInfoActivity extends AppCompatActivity {
    LineChart lineChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_info_layout);
        getSupportActionBar().setTitle("Stock Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int index = getIntent().getIntExtra("index", 0);
        Stock selectedStock = MyApplication.getInstance().getStockFromListByIndex(index);
        TextView symbol = findViewById(R.id.symbol);
        TextView price_per_share = findViewById(R.id.price_per_share);
        TextView market_cap = findViewById(R.id.market_cap);
        TextView volume = findViewById(R.id.volume);

        symbol.setText(selectedStock.getSymbol());
        price_per_share.setText("Current Price: " + selectedStock.getCurrentPrice());
        market_cap.setText("Market Cap: " + selectedStock.getMarketCap());
        volume.setText("Volume " + selectedStock.getVolume());


        //Selling set number of shares

        Button btnSell = findViewById(R.id.btnSell);
        Button btnSellAll = findViewById(R.id.btnSellAll);

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_shares_sold = findViewById(R.id.enter_shares_sold);
                int numSharesSold = Integer.parseInt(enter_shares_sold.getText().toString());
                MyApplication.getInstance().getStockFromListByIndex(index).sellShares(numSharesSold);
                Intent intent = new Intent(StockInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        //Selling ALL shares (remove stock from list completely)
        btnSellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove stock from list
                MyApplication.getInstance().removeStockFromList(index);
                Intent intent = new Intent(StockInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
