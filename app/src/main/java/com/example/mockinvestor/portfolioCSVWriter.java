package com.example.mockinvestor;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class portfolioCSVWriter {

    public static void makePortfolioCSV(ArrayList<String> strings, int portfolioSize) throws IOException {
        File csvDirectory = new File("/data/local/tmp/", "CSVFiles");
        if (!csvDirectory.exists()) {
            csvDirectory.mkdirs();
        } else {
            String filePathStr ="/data/local/tmp/CSVFiles/Portfolio.txt";
            File filePath = new File(filePathStr);
            if (filePath.exists()){
                filePath.delete();
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Portfolio.txt"))) {
            for (int i = 0; i < portfolioSize; i++) {
                writer.append(strings.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
