package com.example.mockinvestor;

import android.app.Application;

import java.util.ArrayList;

public class MyApplication extends Application {
    private static MyApplication instance;
    //ArrayList<Stock> allUserStocks = new ArrayList<>();
    private Portfolio userPortfolio; //list of stocks stored in portfolio object

    public static MyApplication getInstance() {
        return instance;
    }
    public Portfolio getPortfolio(){ return userPortfolio; }

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public boolean containsStock(Stock stock) {
        for(int i = 0; i < this.userPortfolio.getNumStocks(); i++) {
            if (this.userPortfolio.getStockList().get(i).getSymbol().equals(stock.getSymbol())) {
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
        this.userPortfolio.setStocksList(allUserStocks);
    }

    public ArrayList<Stock> getAllUserStocks() {
        return userPortfolio.getStockList();
    }

    public void addStockToList(Stock stock) {
        this.userPortfolio.getStockList().add(stock);
    }

    public void removeStockFromList(Stock stock) {
        this.userPortfolio.getStockList().remove(stock);
    }

    public void removeStockFromList(int index) {
        this.userPortfolio.getStockList().remove(index);
    }

    public void clearStockList() {
        this.userPortfolio.getStockList().clear();
    }

    public Stock getStockFromListByIndex(int index) {
        return this.userPortfolio.getStockList().get(index);
    }



}


