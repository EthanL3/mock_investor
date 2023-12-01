package com.example.mockinvestor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Stock> stocks = new ArrayList<>();
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));
        stocks.add(new Stock("APPL","1", "2", "3"));

        RecyclerView recyclerView = findViewById(R.id.recView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new StockAdapter(getApplicationContext(), stocks));

    }

}
