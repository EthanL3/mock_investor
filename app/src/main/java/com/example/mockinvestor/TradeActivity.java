package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating new stock object
                EditText ticker_input = findViewById(R.id.enter_ticker);
                EditText shares_input = findViewById(R.id.enter_shares);
                String ticker = ticker_input.getText().toString();
                int shares = Integer.parseInt(shares_input.getText().toString());
                Stock user_stock = new Stock(ticker, shares);

                //if stock already exists in the list, add the shares to the existing stock
                if (MyApplication.getInstance().containsStock(user_stock)) {
                    int stockIndex = MyApplication.getInstance().getAllUserStocks().indexOf(user_stock);
                    Stock stock = MyApplication.getInstance().getAllUserStocks().get(stockIndex);
                    stock.buyShares(shares);
                }
                //else add the stock to the list
                else {
                    MyApplication.getInstance().addStockToList(user_stock);
                }
                //going back to main activity
                Intent intent = new Intent(TradeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
