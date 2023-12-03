package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();
    int portfolioSize = 0;
    double holdings = 0, cash = 100000;
    public double getCash(){
        return cash;
    }
    public void setCash(double val){
        this.cash = val;
    }

    public double getHoldings() {
        holdings = 0;
        for (int i = 0; i < portfolioSize; i++) {
            holdings += allUserStocks.get(i).getCurrentValue();
        }
        return holdings;
    }

    public void setHoldings(double holdings) {
        this.holdings = holdings;
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
                allUserStocks.get(allUserStocks.size()-1).buyShares(shares);
            }
            cash = cash - stock.getCurrentValue();
        } catch (NullPointerException e) {
            addStockToList(stock);
            allUserStocks.get(0).buyShares(shares);
            cash = cash - stock.getCurrentValue();
            throw e;
        }
    }

    public void sellStocks(Stock stock, int shares) {
        try {
            if (containsStock(stock)) {
                int indexOfStock = allUserStocks.indexOf(stock);
                Stock user_stock = allUserStocks.get(indexOfStock);
                if (shares == user_stock.getShares()) {
                    removeStockFromList(stock);
                    portfolioSize--;
                    cash = cash + user_stock.getCurrentValue();
                } else if (shares > user_stock.getShares()){
                    System.out.println("Error: SellStocks: You own fewer shares than you want to sell.");
                } else {
                    double previousCurrentVal = user_stock.getCurrentValue();
                    user_stock.sellShares(shares);
                    cash = cash + (previousCurrentVal - user_stock.getCurrentValue());
                }
            } else {
               System.out.println("Error: SellStocks: This stock does not exist in your portfolio.");
            }
        } catch (NullPointerException nullPointerException) {
            System.out.println("Error: You have no stocks to sell.");
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
        for(int i = 0; i < portfolioSize; i++) {
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
        try {
            return this.allUserStocks.get(index);
        } catch (NullPointerException e) {
            throw e;
        }
    }

}

