package com.example.mockinvestor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class StockInfoActivity extends AppCompatActivity {
    LineChart stockLineChart;
    Stock stockToBeSold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stock_info_layout);
        getSupportActionBar().setTitle("Stock Info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int index = getIntent().getIntExtra("index", 0);
        stockToBeSold = MyApplication.getInstance().getStockFromListByIndex(index);

        TextView symbol = findViewById(R.id.symbol);
        TextView current_price = findViewById(R.id.current_price);
        TextView purchase_price = findViewById(R.id.purchase_price);
        TextView current_value = findViewById(R.id.current_value);
        TextView purchase_value = findViewById(R.id.purchase_value);
        TextView days_since_purchase = findViewById(R.id.days_since_purchase);
        TextView purchase_date = findViewById(R.id.purchase_day);
        TextView gain_loss_dollars = findViewById(R.id.gain_loss_dollars);
        TextView gain_loss_percent = findViewById(R.id.gain_loss_percent);
        TextView current_volume = findViewById(R.id.current_volume);
        TextView current_day = findViewById(R.id.current_day);
        TextView stock_name = findViewById(R.id.stock_name);

        Button btnSell = findViewById(R.id.btnSell);
        Button btnSellAll = findViewById(R.id.btnSellAll);

        symbol.setText(stockToBeSold.getSymbol());
        current_price.setText(String.format("Current Price: $%.2f", stockToBeSold.getCurrentPrice()));
        purchase_price.setText(String.format("Purchase Price: $%.2f", stockToBeSold.getPurchasePrice()));
        current_value.setText(String.format("Current Value: $%.2f", stockToBeSold.getCurrentValue()));
        purchase_value.setText(String.format("Purchase Value: $%.2f", stockToBeSold.getPurchaseValue()));
        gain_loss_dollars.setText(String.format("Gain/Loss ($): $%.2f", stockToBeSold.getGainLossDollars()));
        gain_loss_percent.setText(String.format("Gain/Loss (%%): %.2f%%", stockToBeSold.getGainLossPercent()));
        current_volume.setText(String.format("Current Volume: %.2f", stockToBeSold.getVolume()));
        purchase_date.setText(String.format("Purchase Day: %d", stockToBeSold.getPurchaseDay()));
        days_since_purchase.setText(String.format("Days Since Purchase: %d", stockToBeSold.getDaysSincePurchase()));
        current_day.setText(String.format("Current Day: %d", MyApplication.getInstance().getDayCount()));
        stock_name.setText(stockToBeSold.getName());

        stockLineChart = (LineChart) findViewById(R.id.stockLineChart);
        stockToBeSold.setDataVals();
        LineDataSet lineDataSet1 = new LineDataSet(stockToBeSold.getDataVals(), stockToBeSold.getSymbol());
        lineDataSet1.setColor(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
        lineDataSet1.setValueTextColor(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
        lineDataSet1.setDrawFilled(true);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);
        LineData data = new LineData(dataSets);
        stockLineChart.getXAxis().setTextColor(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
        stockLineChart.getAxisLeft().setTextColor(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
        stockLineChart.setDescription(null);
        stockLineChart.getAxisRight().setEnabled(false);
        stockLineChart.getXAxis().setPosition(com.github.mikephil.charting.components.XAxis.XAxisPosition.BOTTOM);
        stockLineChart.getLegend().setTextColor(Color.WHITE);
        Entry purchaseDayEntry = lineDataSet1.getEntryForIndex(stockToBeSold.getPurchaseDay());

        //highlight purchase day data point on linechart:
        stockLineChart.setData(data);
        stockLineChart.invalidate();
        purchaseDayEntry.setIcon(ResourcesCompat.getDrawable(getResources(), R.drawable.ic_add_circle, null));


        btnSell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText enter_shares_sold = findViewById(R.id.enter_shares_sold);
                int numSharesSold = Integer.parseInt(enter_shares_sold.getText().toString());
                MyApplication.getInstance().sellStocks(stockToBeSold,numSharesSold);
                //going back to main activity
                Intent intent = new Intent(StockInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Selling ALL shares (remove stock from list completely)
        btnSellAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //remove stock from list
                MyApplication.getInstance().removeStockFromList(index);
                Intent intent = new Intent(StockInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
