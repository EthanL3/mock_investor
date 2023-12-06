package com.example.mockinvestor;

import android.app.Application;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class MyApplication extends Application {
    private static MyApplication instance;
    ArrayList<Stock> allUserStocks = new ArrayList<>();
    double holdings = 0, availableCash = 100000;
    double totalValueOfStocks = 0;
    int dayCount;

    public int getDayCount(){
        return dayCount;
    }
    public void incrementDayCount(){
        if (dayCount < 100) {
            this.dayCount += 1;
            for(int i = 0; i < allUserStocks.size(); i++) {
                allUserStocks.get(i).setDaysSincePurchase(allUserStocks.get(i).getDaysSincePurchase() + 1);
            }
        }
        else {
            this.dayCount = 0;
        }
    }

    public double getAvailableCash(){
        return availableCash;
    }
    public void setAvailableCash(double val){ this.availableCash = val; }

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
                allUserStocks.get(allUserStocks.indexOf(stock)).buyShares(shares);
            } else {
                addStockToList(stock);
                allUserStocks.get(allUserStocks.indexOf(stock)).buyShares(shares);
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
                    availableCash += stock.getCurrentValue();
                } else {
                    stock.sellShares(shares);
                    availableCash += (shares * stock.getCurrentPrice());
                }
            } else {
                Toast.makeText(this, "Error: SellStocks: You do not own this stock.", Toast.LENGTH_SHORT).show();
            }
        } catch (NullPointerException nullPointerException) {
            Toast.makeText(this, "Error: SellStocks: You do not own this stock.", Toast.LENGTH_SHORT).show();
        }
    }

    //Saving portfolio code
    public void saveStocks(){ //to save in Portfolio.csv
        try {
            String data;
            PortfolioCSVWriter.makePortfolioCSV();
            data = String.valueOf(availableCash) + "," + String.valueOf(dayCount) + "\n";
            //line: symbol, purchasePrice, currentPrice, volume, daysSincePurchase, shares, currentValue, dayCount
            for (int i = 0; i < allUserStocks.size(); i++) {
                Stock currentStock = allUserStocks.get(i);
                data = currentStock.getSymbol() + "," + String.valueOf(currentStock.getPurchasePrice()) + "," + String.valueOf(currentStock.getVolume());
                data += "," + String.valueOf(currentStock.getDaysSincePurchase()) + "," + String.valueOf(currentStock.getShares());
                data += "," + String.valueOf(availableCash) + "," + String.valueOf(dayCount) + "\n";
                PortfolioCSVWriter.addToPortfolioCSV(data);
            }
        } catch (IOException e){
            Toast.makeText(this, "Unable to Save Stocks", Toast.LENGTH_SHORT).show();
        }
    }
    public void loadStocksAtOpen() {
        FileReader fr = null;
        String filePathStr ="/data/user/0/com.example.mockinvestor/files/CSVFiles/Portfolio.csv";
        File csvFile = new File(filePathStr);
        if (csvFile.exists())
        {
            try {
                fr = new FileReader(csvFile);
                BufferedReader br = new BufferedReader(fr);
                //line: symbol, purchasePrice, volume, daysSincePurchase, shares, currentValue, dayCount;
                String line;
                while ((line = br.readLine()) != null) {
                    String [] dat = line.split(",");
                    Stock currentStock = new Stock(dat[0], Double.parseDouble(dat[1]), Double.parseDouble(dat[2]));
                    currentStock.setDaysSincePurchase((int) Double.parseDouble(dat[3]));
                    currentStock.setShares((int) Double.parseDouble(dat[4]));
                    currentStock.setCurrentPrice(CSVReader.getClosePrice(dayCount, currentStock.getSymbol()));
                    currentStock.setCurrentValue(currentStock.getCurrentPrice() * currentStock.getShares());
                    currentStock.setPurchaseValue(currentStock.getPurchasePrice() * currentStock.getShares());
                    currentStock.setGainLossDollars(currentStock.getCurrentValue() - currentStock.getPurchaseValue());
                    currentStock.setGainLossPercent((currentStock.getCurrentValue() - currentStock.getPurchaseValue()) / currentStock.getPurchaseValue());
                    addStockToList(currentStock);
                    dayCount = (int) Double.parseDouble(dat[6]);
                }
                br.close();
                for (Stock stock : allUserStocks) {
                    availableCash -= stock.getPurchaseValue();
                }
            } catch (FileNotFoundException e) {
                Toast.makeText(this, "File not found", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } catch (IOException e) {
                Toast.makeText(this, "Error reading file", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }
        }
        else {
            Toast.makeText(this, "File does not exist", Toast.LENGTH_SHORT).show();
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


    public boolean containsStock(String ticker) {
        for(int i = 0; i < this.allUserStocks.size(); i++) {
            if (this.allUserStocks.get(i).getSymbol().equals(ticker)) {
                return true;
            }
        }
        return false;
    }
    public void updateStockData() {
        for(int i=0; i<allUserStocks.size(); i++){
            String ticker = this.allUserStocks.get(i).getSymbol();
            Stock currentStock = this.allUserStocks.get(i);
            currentStock.setCurrentPrice(CSVReader.getClosePrice(dayCount, ticker));
            currentStock.setCurrentValue(currentStock.getCurrentPrice() * currentStock.getShares());
            currentStock.setGainLossDollars(currentStock.getCurrentValue() - currentStock.getPurchaseValue());
            currentStock.setGainLossPercent((currentStock.getCurrentValue() - currentStock.getPurchaseValue()) / currentStock.getPurchaseValue());
        }
    }

    public void resetApp()
    {
        this.availableCash = 100000;
        this.dayCount = 0;
        this.allUserStocks.clear();
        this.totalValueOfStocks = 0;
        this.holdings = 0;
        saveStocks();
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


