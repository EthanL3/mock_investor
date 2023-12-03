package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();

    double holdings = 0, cash = 100000;
    int numStocks = 0;

    public int getNumStocks(){
        return numStocks;
    }
    public void setNumStocks(int val){
        this.numStocks = val;
    }
    public double getCash(){
        return cash;
    }
    public void setCash(double val){
        this.cash = val;
    }
    public void purchaseStocks(Stock stock, int shares) {
        int count = 0;
        while (count <= numStocks) {
            if (allUserStocks.get(count).getSymbol() == stock.getSymbol()) { //add shares if stock already in portfolio
                break;
            }
            count++;
        }
        if (count > numStocks) { //then this stock doesn't already exist in portfolio
            allUserStocks.add(stock);
        }
        allUserStocks.get(count).buyShares(shares);
        cash = cash - allUserStocks.get(count).getTOTALCurrentValue();
        numStocks++;
    }
    
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


