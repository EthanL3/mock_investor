package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();
    double holdings = 0, availableCash = 100000;
    int portfolioSize = 0;
    double totalValueOfStocks = 0;
    int timerCount = 0;

    public int getDayCount(){
        return timerCount;
    }
    public void incrementDayCount(){
        if (timerCount < 100) {this.timerCount += 1;}
        else {this.timerCount = 0; } }

    public double getAvailableCash(){
        return availableCash;
    }
    //public void setAvailableCash(double val){ this.availableCash = val; }

    public int getPortfolioSize(){ return portfolioSize; }
    public double getTotalValueOfStocks() {
        totalValueOfStocks = 0;
        for(int i = 0; i < allUserStocks.size(); i++) {
            totalValueOfStocks += allUserStocks.get(i).getCurrentValue();
        }
        return totalValueOfStocks;
    }
    public double getHoldings() {
        holdings = availableCash;
        for(int i = 0; i < allUserStocks.size(); i++) {
            holdings += allUserStocks.get(i).getCurrentValue();
        }
        return holdings;
    }
    public void purchaseStocks(Stock stock, int shares) {
        try {
            if (containsStock(stock)) {
                int indexOfStock = allUserStocks.indexOf(stock);
                Stock user_stock = allUserStocks.get(indexOfStock);
                user_stock.buyShares(shares);
            } else {
                addStockToList(stock);
                portfolioSize++;
                allUserStocks.get(portfolioSize-1).buyShares(shares);
            }
            availableCash = availableCash - stock.getPurchaseValue();
        } catch (NullPointerException e) {
            addStockToList(stock);
            allUserStocks.get(0).buyShares(shares);
            availableCash= availableCash - stock.getPurchaseValue();
            throw e;
        }
    }

    public void sellStocks(Stock stock, int shares) {
        try {
            if (containsStock(stock)) {
                int indexOfStock = allUserStocks.indexOf(stock);
                Stock user_stock = allUserStocks.get(indexOfStock);
                if (shares >= user_stock.getShares()) {
                    removeStockFromList(stock);
                    portfolioSize--;
                    availableCash = availableCash + user_stock.getCurrentValue();
                } else {
                    double previousCurrentVal = user_stock.getCurrentValue();
                    user_stock.sellShares(shares);
                    availableCash = availableCash + (previousCurrentVal - user_stock.getCurrentValue());
                }
            } else {
                System.out.println("Error: SellStocks: This stock does not exist in your portfolio.");
            }
        } catch (NullPointerException nullPointerException) {
            System.out.println("Error: SellStocks: You have no stocks to sell.");
        }
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


