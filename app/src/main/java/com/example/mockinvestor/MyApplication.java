package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();
    public static MyApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    /* NOT NEEDED FOR NOW, maybe need it later tho
    public void sellShares(int index, int numSharesSold) {
        Stock stock = this.allUserStocks.get(index);
        if (stock.getQuantity() == 0) {
            this.removeStockFromList(index);
        }
        else {
            stock.sellShares(numSharesSold);
        }
    }
     */

    public void setAllUserStocks(ArrayList<Stock> allUserStocks) {
        this.allUserStocks = allUserStocks;
    }

    public ArrayList<Stock> getAllUserStocks() {
        return allUserStocks;
    }

    public void addStockToList(Stock stock) {
        this.allUserStocks.add(stock);
    }

    public void removeStockFromList(Stock stock) {
        this.allUserStocks.remove(stock);
    }

    public void removeStockFromList(int index) {
        this.allUserStocks.remove(index);
    }

    public void clearStockList() {
        this.allUserStocks.clear();
    }

    public Stock getStockFromListByIndex(int index) {
        return this.allUserStocks.get(index);
    }



}


