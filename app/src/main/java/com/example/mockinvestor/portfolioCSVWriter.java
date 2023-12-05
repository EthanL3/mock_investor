package com.example.mockinvestor;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class portfolioCSVWriter {

    public static void makePortfolioCSV(ArrayList<String> strings) throws IOException {
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
        File csvFile = new File(csvDirectory, "Portfolio.csv");
        FileWriter writer = new FileWriter(csvFile);
        for (int i=0; i<strings.size();i++){
            writer.append(strings.get(i) + "\n");
        }
        writer.flush();
        writer.close();
    }
}
