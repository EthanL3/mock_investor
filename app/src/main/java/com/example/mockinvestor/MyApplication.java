package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();
    double holdings = 0, cash = 100000;
    public double getCash(){
        return cash;
    }
    public void setCash(double val){
        this.cash = val;
    }

    public double getHoldings() {
        holdings = 0;
        for(int i = 0; i < allUserStocks.size(); i++) {
            holdings += allUserStocks.get(i).getCurrentValue();
        }
        return holdings;
    }

    public void setHoldings(double holdings) {
        this.holdings = holdings;
    }
    /*
    public void purchaseStocks(Stock stock, int shares) {
        if (containsStock(stock)) {
            int indexOfStock = allUserStocks.indexOf(stock);
            allUserStocks.get(indexOfStock).buyShares(shares);
            cash = cash - allUserStocks.get(indexOfStock).getTOTALCurrentValue();
        }
        //if stock is not in list, index will always be size - 1
        else {
            addStockToList(stock);
            allUserStocks.get(allUserStocks.size()-1).buyShares(shares);
        }
        cash = cash - (allUserStocks.get(allUserStocks.size()-1).getCurrentValue()*shares);
    }

    public void sellStocks(Stock stock, int shares) {
        if (shares >= stock.getQuantity()) {
            removeStockFromList(stock);
        }
        else {
            allUserStocks.get(allUserStocks.indexOf(stock)).sellShares(shares);
        }
        cash = cash + stock.getTOTALCurrentValue();
    }
    */

    public static MyApplication getInstance() {
        return instance;
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public boolean containsStock(Stock stock) {
        for(int i = 0; i < this.allUserStocks.size(); i++) {
            if (this.allUserStocks.get(i).getSymbol().equals(stock.getSymbol())) {
                return true;
            }
        }
        return false;
    }

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


