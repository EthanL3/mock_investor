/*
package com.example.mockinvestor;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class portfolioCSVReader {

    public static ArrayList<Stock> loadSavedStocks(){
        ArrayList<Stock> savedStocks = new ArrayList<>();
        try {
            File csvFile = new File("/data/user/0/com.example.mockinvestor/files/", "CSVFiles");
            CSVReader reader = new CSVReader(new FileReader(csvFile.getAbsolutePath()));
            String [] nextLine;
            Stock currentStock;
            while ((nextLine = reader.readNext()) != null) {
                String [] dat = nextLine[0].split(",");
                currentStock = new Stock(dat[0], Float.parseFloat(dat[1]), Integer.parseInt(dat[2]), dat[3]);
                currentStock.buyShares(Integer.parseInt(dat[4]));
                currentStock.buyShares(Integer.parseInt(dat[4]));
                currentStock.updateDay(Float.parseFloat(dat[5]));
                savedStocks.add(currentStock);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return savedStocks;
    }

    public static boolean containsStock(String Symbol){
        ArrayList<Stock> savedStocks = new ArrayList<>();
        String fileP ="/data/user/0/com.example.mockinvestor/files/CSVFiles/Portfolio.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(fileP))){
            br.readLine();
            String l;
            String d;
            while ((l = br.readLine()) != null){
                String[] dat = l.split(",");
                if (dat[0] == Symbol) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
*/