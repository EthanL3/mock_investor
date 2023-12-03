package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TradeActivity extends AppCompatActivity {

    ArrayList<Stock> added_stocks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        getSupportActionBar().setTitle("Trade");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TextView total_money_left = findViewById(R.id.total_money_left);

        //Money left to spend, placeholder for now
        //total_money_left.setText("Total money left: $100000");


        Button btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating new stock object
                EditText ticker_input = findViewById(R.id.enter_ticker);
                EditText shares_input = findViewById(R.id.enter_shares);
                String ticker = ticker_input.getText().toString();
                int shares = Integer.parseInt(shares_input.getText().toString());
                Stock user_stock = new Stock(ticker, shares);
                MyApplication.getInstance().purchaseStocks(user_stock,shares);
                //going back to main activity
                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
