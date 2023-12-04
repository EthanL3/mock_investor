package com.example.mockinvestor;

import com.example.mockinvestor.avApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import com.example.mockinvestor.avApi.UpdateCallback;

//import com.example.mockinvestor.BuildConfig;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Portfolio");

        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        ArrayList<Stock> stocks = new ArrayList<>();
        //Buttons
        Button btnProfile = findViewById(R.id.btnProfile);
        Button btnTrade = findViewById(R.id.btnTrade);
        Button btnRefresh = findViewById(R.id.btnRefresh);
        Button btnQuit = findViewById(R.id.btnQuit);

        //TextView objects for cash left and holdings
        TextView total_value_of_stocks = findViewById(R.id.total_value_of_stocks);
        total_value_of_stocks.setText(String.format("Total value of stocks: $%.2f", MyApplication.getInstance().getTotalValueOfStocks()));

        //if user has stocks, display them
        if(!MyApplication.getInstance().getAllUserStocks().isEmpty()) {
            stocks = MyApplication.getInstance().getAllUserStocks();
        }

        //Button clicks
        btnTrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                Intent intent = new Intent(MainActivity.this, TradeActivity.class);
                startActivity(intent);
            }
        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //update here?
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });

        StockAdapter adapter = new StockAdapter(this, stocks, this);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
        int portfolioSize = MyApplication.getInstance().getPortfolioSize();
        Timer currentPriceTimer = new Timer();
        while (MyApplication.getInstance().getCount() < 100) {
            currentPriceTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    for (int i=0; i<portfolioSize; i++) {
                        String currentSymbol = MyApplication.getInstance().getAllUserStocks().get(i).getSymbol();
                        MyApplication.getInstance().getAllUserStocks().get(i).updateDay(CSVReader.getClosePrice(MyApplication.getInstance().getCount(),currentSymbol));
                        adapter.notifyDataSetChanged();
                    }
                }
            }, 10000); //delay 10 seconds
            MyApplication.getInstance().incrementCount();
        }

        //avApi test
        //will save the csv to this path in the device's files:
        // /data/user/0/com.example.mockinvestor/files/CSVFiles/"symbol"_historical_stock_data.csv
 /*       avApi apiObj = new avApi();

        String symbol = "AMD";

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

        apiObj.runStockDataUpdateWithRetry(this, symbol, 10, updateCallback);
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

