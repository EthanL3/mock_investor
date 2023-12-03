package com.example.mockinvestor;

import com.example.mockinvestor.avApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

//import com.example.mockinvestor.BuildConfig;

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



        //avApi test
        //will save the csv to this path in the device's files:
        // /data/user/0/com.example.mockinvestor/files/CSVFiles/"symbol"_historical_stock_data.csv
        /*
        avApi apiObj = new avApi();

        String symbol = "NFLX";
        apiObj.stockDataUpdate(this, symbol);

        Float testPrice = CSVReader.getClosePrice(0, symbol);
        System.out.println("testPrice: " + testPrice);

        Float testVolume = CSVReader.getVolume(0, symbol);
        System.out.println("testVolume: " + testVolume);
        */

    }



    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, StockInfoActivity.class);
        //pass the current stock object to the next activity
        intent.putExtra("index", position);
        startActivity(intent);
    }
}
