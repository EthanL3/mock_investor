package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btnReset = findViewById(R.id.btnReset);

        available_cash.setText(String.format("Available Cash: $%.2f", MyApplication.getInstance().getAvailableCash()));
        current_total_holdings.setText(String.format("Holdings: $%.2f", MyApplication.getInstance().getHoldings()));
        total_gain_loss.setText(String.format("Gains/Losses: $%.2f", MyApplication.getInstance().getHoldings() - 100000));
        total_value_of_stocks.setText(String.format("Portfolio Value: $%.2f", MyApplication.getInstance().getTotalValueOfStocks()));
        current_day_count.setText(String.format("Day: %d", MyApplication.getInstance().getDayCount()));

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyApplication.getInstance().resetApp();
                Toast.makeText(ProfileActivity.this, "App Reset", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
