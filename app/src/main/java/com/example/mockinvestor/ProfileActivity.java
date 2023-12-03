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

        TextView cash_left = findViewById(R.id.cash_left);
        TextView cash_gain_loss = findViewById(R.id.cash_gain_loss);
        TextView total_value_of_stocks = findViewById(R.id.total_value_of_stocks);

        cash_left.setText("Cash Left: $" + Double.toString(MyApplication.getInstance().getCash()));
        total_value_of_stocks.setText("Total Value of Stocks: $" + Double.toString(MyApplication.getInstance().getHoldings()));

    }
}
