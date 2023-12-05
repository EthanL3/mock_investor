package com.example.mockinvestor;

import android.app.Application;

import java.io.File;
import java.io.IOException;
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
        else {this.timerCount = 0; }
    }

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
                if (shares >= stock.getShares()) {
                    removeStockFromList(stock);
                    portfolioSize--;
                    availableCash = availableCash + stock.getCurrentValue();
                } else {
                    stock.sellShares(shares);
                    availableCash = availableCash + (shares * stock.getCurrentPrice());
                }
            } else {
                System.out.println("Error: SellStocks: This stock does not exist in your portfolio.");
            }
        } catch (NullPointerException nullPointerException) {
            System.out.println("Error: SellStocks: You have no stocks to sell.");
        }
    }

    //Saving portfolio code
    public void saveStocks(){ //to save in Portfolio.csv
        try {
            String data;
            portfolioCSVWriter.makePortfolioCSV();
            //line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
            for (int i = 0; i < portfolioSize; i++) {
                Stock currentStock = allUserStocks.get(i);
                data = currentStock.getSymbol() + "," + String.valueOf(currentStock.getPurchasePrice()) + "," + String.valueOf(currentStock.getVolume());
                data = data + "," + currentStock.getPurchaseDate() + "," + String.valueOf(currentStock.getShares());
                data = data + "," + String.valueOf(currentStock.getCurrentValue()) + "\n";
                portfolioCSVWriter.addToPortfolioCSV(data);
            }
        } catch (IOException e){
            System.out.println("Error: Save: Couldn't save your progress");
        }
    }
    public void loadStocksAtOpen(){
        allUserStocks = portfolioCSVReader.loadSavedStocks();
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

    public void updateStockData() {
        for(int i=0; i<portfolioSize; i++){
            String ticker = this.allUserStocks.get(i).getSymbol();
            this.allUserStocks.get(i).updateDay(CSVReader.getClosePrice(timerCount,ticker));
        }
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


