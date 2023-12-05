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
        TextView current_day_count = findViewById(R.id.current_day_count);

        available_cash.setText(String.format("Available Cash: $%.2f", MyApplication.getInstance().getAvailableCash()));
        current_total_holdings.setText(String.format("Holdings: $%.2f", MyApplication.getInstance().getHoldings()));
        total_gain_loss.setText(String.format("Gains/Losses: $%.2f", MyApplication.getInstance().getHoldings() - 100000));
        total_value_of_stocks.setText(String.format("Portfolio Value: $%.2f", MyApplication.getInstance().getTotalValueOfStocks()));
        current_day_count.setText(String.format("Day: %d", MyApplication.getInstance().getDayCount()));
    }
}
