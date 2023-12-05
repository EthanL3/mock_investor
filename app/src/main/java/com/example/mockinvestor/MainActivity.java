package com.example.mockinvestor;

import com.example.mockinvestor.avApi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
        Button btnSave = findViewById(R.id.btnSave);

        //TextView objects for cash left and holdings
        TextView total_value_of_stocks = findViewById(R.id.total_value_of_stocks);
        MyApplication.getInstance().loadStocksAtOpen();
        total_value_of_stocks.setText(String.format("Total value of stocks: $%.2f", MyApplication.getInstance().getTotalValueOfStocks()));
        if (!(MyApplication.getInstance().allUserStocks.isEmpty()))
        {
            //if user has stocks, display them
            stocks = MyApplication.getInstance().getAllUserStocks();
            double cashToSubtract = 0;
            for (Stock stock : stocks) {
                cashToSubtract += stock.getCurrentValue();

            }
            MyApplication.getInstance().setAvailableCash(MyApplication.getInstance().getAvailableCash() - cashToSubtract);
            MyApplication.getInstance().setHoldings(MyApplication.getInstance().getAvailableCash() + MyApplication.getInstance().getTotalValueOfStocks());
        }


        StockAdapter adapter = new StockAdapter(this, stocks, this);
        recycler_view.setAdapter(adapter);
        recycler_view.setLayoutManager(new LinearLayoutManager(this));

        Timer currentPriceTimer = new Timer();
        currentPriceTimer.schedule(new TimerTask() {
            @Override
                public void run() {
                MyApplication.getInstance().incrementDayCount();
            }
        }, 10000); //delay 10 seconds


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
                //MyApplication.getInstance().loadStocksAtOpen();
                //adapter.notifyDataSetChanged();
                MyApplication.getInstance().updateStockData();
                adapter.notifyDataSetChanged();
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Do something when the button is clicked
                MyApplication.getInstance().saveStocks();
                Toast.makeText(MainActivity.this, "Stocks saved", Toast.LENGTH_SHORT).show();
            }
        });

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

