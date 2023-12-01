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
    private ArrayList<Stock> stocks = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Portfolio");

        RecyclerView recycler_view = findViewById(R.id.recycler_view);



        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));
        stocks.add(new Stock("APPL", "Apple Inc.", "10", "1000", "-50"));

        Button btnTrade = findViewById(R.id.btnTrade);
        btnTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                startActivity(new Intent(MainActivity.this, TradeActivity.class));
            }
        });

        StockAdapter adapter = new StockAdapter(this, stocks, this);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
    }
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, StockInfoActivity.class);
        //pass the entire stock object to the next activity
        intent.putExtra("stock", stocks.get(position));


        startActivity(intent);
    }
}
