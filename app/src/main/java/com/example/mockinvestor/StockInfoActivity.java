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
        Stock stockToBeSold = MyApplication.getInstance().getStockFromListByIndex(index);

        TextView symbol = findViewById(R.id.symbol);
        TextView current_price = findViewById(R.id.current_price);
        TextView purchase_price = findViewById(R.id.purchase_price);
        TextView current_value = findViewById(R.id.current_value);
        TextView purchase_value = findViewById(R.id.purchase_value);
        TextView current_date = findViewById(R.id.current_date);
        TextView purchase_date = findViewById(R.id.purchase_date);
        TextView gain_loss = findViewById(R.id.gain_loss);
        TextView current_volume = findViewById(R.id.current_volume);

        Button btnSell = findViewById(R.id.btnSell);
        Button btnSellAll = findViewById(R.id.btnSellAll);


        symbol.setText(stockToBeSold.getSymbol());
        current_price.setText(String.format("Current Price: $%.2f", stockToBeSold.getCurrentPrice()));
        purchase_price.setText(String.format("Purchase Price: $%.2f", stockToBeSold.getPurchasePrice()));
        current_value.setText(String.format("Current Value: $%.2f", stockToBeSold.getCurrentValue()));
        purchase_value.setText(String.format("Purchase Value: $%.2f", stockToBeSold.getPurchaseValue()));
        gain_loss.setText(String.format("Gain/Loss($/%%): $%.2f", stockToBeSold.getGainLossDollars()) + " / " + String.format("%.2f%%", stockToBeSold.getGainLossPercent()));
        current_volume.setText(String.format("Current Volume: %.2f", stockToBeSold.getVolume()));
        //purchase_date.setText(String.format("Gain/Loss($/%%): $%.2f", stockToBeSold.getPurchaseDate()));
       // current_date.setText(String.format("Gain/Loss($/%%): $%.2f", stockToBeSold.getCurrentDate()));



        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_shares_sold = findViewById(R.id.enter_shares_sold);
                int numSharesSold = Integer.parseInt(enter_shares_sold.getText().toString());
                MyApplication.getInstance().sellStocks(stockToBeSold,numSharesSold);
                //going back to main activity
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
