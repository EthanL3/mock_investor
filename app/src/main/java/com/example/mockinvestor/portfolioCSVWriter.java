package com.example.mockinvestor;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class portfolioCSVWriter {
    static int lines = 0;
    static File csvFile;
    static FileWriter writer;
    public static void makePortfolioCSV() throws IOException {
        File csvDirectory = new File("/data/local/tmp/", "CSVFiles");
        if (!csvDirectory.exists()) {
            csvDirectory.mkdirs();
        } else {
            String filePathStr ="/data/local/tmp/CSVFiles/Portfolio.csv";
            File filePath = new File(filePathStr);
            if (filePath.exists()){
                filePath.delete();
            }
        }
        csvFile = new File(csvDirectory, "Portfolio.csv");
        lines = 0;
    }

    public static void addToPortfolioCSV(String data) throws IOException {
        if (lines == 0){
            writer = new FileWriter(csvFile);
        }
        writer.append(data);
        writer.flush();
        writer.close();
        lines++;
    }

    public static void closeCSV() throws IOException{
        writer.close();
    }

    public static int getNumLines(){ return lines; }
}
