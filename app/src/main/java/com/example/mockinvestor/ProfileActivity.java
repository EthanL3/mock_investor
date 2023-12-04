package com.example.mockinvestor;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView available_cash = findViewById(R.id.available_cash);
        TextView current_total_holdings = findViewById(R.id.current_total_holdings);
        TextView total_gain_loss = findViewById(R.id.total_gain_loss);
        TextView total_value_of_stocks = findViewById(R.id.total_value_of_stocks);

        available_cash.setText(String.format("Cash available to trade: $%.2f", MyApplication.getInstance().getAvailableCash()));
        current_total_holdings.setText(String.format("Current total holdings: $%.2f", MyApplication.getInstance().getHoldings()));
        total_gain_loss.setText(String.format("Total gain/loss: $%.2f", MyApplication.getInstance().getHoldings() - 100000));
        total_value_of_stocks.setText(String.format("Total value of stocks: $%.2f", MyApplication.getInstance().getTotalValueOfStocks()));
    }
}
