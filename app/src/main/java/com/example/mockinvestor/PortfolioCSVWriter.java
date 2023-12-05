package com.example.mockinvestor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//data format:
//line: stock symbol, purchase price, volume, purchase date, shares, current price (at time of closing app)
//line2: stock2 symbol, purchase price, volume, purchase date, shares, current price

public class PortfolioCSVWriter {
    static int lines = 0;
    static File csvFile;
    public static void makePortfolioCSV() throws IOException {
        File csvDirectory = new File("/data/user/0/com.example.mockinvestor/files/", "CSVFiles");
        if (!csvDirectory.exists()) {
            csvDirectory.mkdirs();
        } else {
            String filePathStr ="/data/user/0/com.example.mockinvestor/files/CSVFiles/Portfolio.csv";
            File filePath = new File(filePathStr);
            if (filePath.exists()){
                filePath.delete();
                lines = 0;
            }
        }
        csvFile = new File(csvDirectory, "Portfolio.csv");
    }

    public static void addToPortfolioCSV(String data) throws IOException {
        FileWriter writer = new FileWriter(csvFile);
        writer.append(data);
        writer.flush();
        writer.close();
        lines++;
    }

    public static int getNumLines(){ return lines; }
}
