package com.example.mockinvestor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TradeActivity extends AppCompatActivity {

    ArrayList<Stock> added_stocks = new ArrayList<>();

    double holdings = 0, cash = 100000;
    int numStocks = 0;

    public int getNumStocks(){ return numStocks; }
    public void purchaseStocks(Stock stock, int shares) {
        int count = 0;
        while (count <= numStocks) {
            if (added_stocks.get(count).getSymbol() == stock.getSymbol()) { //add shares if stock already in portfolio
                added_stocks.get(count).updateShares((added_stocks.get(count).getQuantity() + shares));
                break;
            }
            count++;
        }
        if (count > numStocks) { //if stock does not already exist in portfolio
            added_stocks.get(count).updateShares(shares);
        }
        cash = cash - added_stocks.get(count).getTOTALCurrentValue();
        holdings = holdings + added_stocks.get(count).getTOTALCurrentValue();
        numStocks++;
    }
    public void setStocksList(ArrayList<Stock> stocks){
        this.added_stocks = stocks;
    }
    public void sellStocks(Stock stock, int shares) {
        int count = 0;
        int shares_buffer;
        while (count <= numStocks) {
            if (added_stocks.get(count).getSymbol() == stock.getSymbol()) { //add shares if stock already in portfolio
                shares_buffer = added_stocks.get(count).getQuantity() - shares;
                if (shares_buffer < 1) {
                    added_stocks.remove(count);
                } else {
                    added_stocks.get(count).updateShares(shares_buffer);
                }
                break;
            } //if stock is found in portfolio bracket
            count++;
        }
        if (count > numStocks) { //if stock does not already exist in portfolio
            System.out.println("Error: This stock does not exist in your portfolio");
        }
        cash = cash + added_stocks.get(count).getTOTALCurrentValue();
        holdings = holdings - added_stocks.get(count).getTOTALCurrentValue();
        numStocks--;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade);
        getSupportActionBar().setTitle("Trade");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //TextView total_money_left = findViewById(R.id.total_money_left);

        //Money left to spend, placeholder for now
        //total_money_left.setText("Total money left: $100000");


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
                    purchaseStocks(user_stock,shares);
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
