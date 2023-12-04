package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mockinvestor.avApi.UpdateCallback;

import java.util.ArrayList;

public class TradeActivity extends AppCompatActivity {

    ArrayList<Stock> added_stocks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        getSupportActionBar().setTitle("Trade");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Button btnBuy = findViewById(R.id.btnBuy);
        TextView cash_available = findViewById(R.id.cash_available);
        cash_available.setText(String.format("$%.2f", MyApplication.getInstance().getAvailableCash()));
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating new stock object
                EditText ticker_input = findViewById(R.id.enter_ticker);
                EditText shares_input = findViewById(R.id.enter_shares);
                String ticker = ticker_input.getText().toString();
                int shares = Integer.parseInt(shares_input.getText().toString());

                //code that handles buying and creating CSVs
                avApi apiobject = new avApi();
                UpdateCallback updateCallback = new UpdateCallback() {
                    @Override
                    public void onUpdateComplete() {
                        // Handle success, e.g., update UI or perform additional tasks
                        System.out.println("Update completed successfully");
                    }

                    @Override
                    public void onUpdateFailed() {
                        // Handle failure, e.g., show an error message
                        System.out.println("Update failed");
                    }
                };

                apiobject.runStockDataUpdateWithRetry(TradeActivity.this, ticker, 5, updateCallback);
                //Stock user_stock = new Stock(ticker, CSVReader.getClosePrice(0, ticker), CSVReader.getVolume(0, ticker), CSVReader.getDate(0, ticker));
                Stock user_stock = new Stock(ticker, CSVReader.getClosePrice(0, ticker), CSVReader.getVolume(0, ticker), "2023-08-01");


                MyApplication.getInstance().purchaseStocks(user_stock,shares);
                //going back to main activity
                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
