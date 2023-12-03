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
        TextView market_cap = findViewById(R.id.market_cap);
        TextView volume = findViewById(R.id.volume);
        Button btnSell = findViewById(R.id.btnSell);
        Button btnSellAll = findViewById(R.id.btnSellAll);

        symbol.setText(stockToBeSold.getCompanyName()+"("+stockToBeSold.getSymbol()+")");
        current_price.setText("Current Price: " + Double.toString(stockToBeSold.getCurrentPrice()));
        market_cap.setText("Market Cap: " + Double.toString(stockToBeSold.getMarketCap()));
        volume.setText("Volume " + Double.toString(stockToBeSold.getVolume()));

        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_shares_sold = findViewById(R.id.enter_shares_sold);
                int numSharesSold = Integer.parseInt(enter_shares_sold.getText().toString());
                //if number of shares sold >= total shares of stock, remove stock from list
                if(numSharesSold >= stockToBeSold.getShares()){
                    MyApplication.getInstance().removeStockFromList(index);
                }
                //else sell number of shares
                else
                {
                    stockToBeSold.sellShares(numSharesSold);
                }
                //updating cash amount
                MyApplication.getInstance().setCash(MyApplication.getInstance().getCash() + stockToBeSold.getCurrentValue());
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
