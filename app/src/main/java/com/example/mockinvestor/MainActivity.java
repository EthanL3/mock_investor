package com.example.mockinvestor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Portfolio");

        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        ArrayList<Stock> stocks = new ArrayList<>();

        if(!MyApplication.getInstance().getAllUserStocks().isEmpty()) {
            stocks = MyApplication.getInstance().getAllUserStocks();
        }
        else {
            stocks.add(new Stock("APPL", 10));
            stocks.add(new Stock("GOOG", 20));
            stocks.add(new Stock("AMZN", 30));
            stocks.add(new Stock("FB", 40));
            stocks.add(new Stock("TSLA", 50));
            stocks.add(new Stock("MSFT", 60));
            stocks.add(new Stock("NVDA", 70));
            stocks.add(new Stock("AMD", 80));
            stocks.add(new Stock("INTC", 90));
            stocks.add(new Stock("QCOM", 100));
        }

        Button btnTrade = findViewById(R.id.btnTrade);

        btnTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                Intent intent = new Intent(MainActivity.this, TradeActivity.class);
                startActivity(intent);
            }
        });

        StockAdapter adapter = new StockAdapter(this, stocks, this);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, StockInfoActivity.class);
        intent.putExtra("index", position); //pass the index of the stock object in the arraylist
        //pass the entire stock object to the next activity
        startActivity(intent);
    }
}
