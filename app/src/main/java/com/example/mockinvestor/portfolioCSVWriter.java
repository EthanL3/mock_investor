package com.example.mockinvestor;

import android.content.Context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class portfolioCSVWriter {
    public static void writeStringToCSV(Context context, String data) throws IOException {
        File csvDirectory = new File(context.getFilesDir(), "CSVFiles");
        if (!csvDirectory.exists()) {
            csvDirectory.mkdirs();
        }
        File csvFile = new File(csvDirectory, "Portfolio.csv");
        FileWriter writer = new FileWriter(csvFile);
        writer.append(data);
        writer.flush();
        writer.close();
    }
}
