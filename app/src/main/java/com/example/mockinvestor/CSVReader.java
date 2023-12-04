package com.example.mockinvestor;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static String getFilepath(String symbol){
        String fileP ="/data/user/0/com.example.mockinvestor/files/CSVFiles/" + symbol + "_historical_stock_data.csv";
        return fileP;
    }
    public static List<Float> readClosingPrices(String symbol){
        String filepath = getFilepath(symbol);
        ArrayList<Float> closingPrices = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            br.readLine();
            String l;
            float cPrice;
            while ((l = br.readLine()) != null){
                String[] dat = l.split(",");
                cPrice = Float.parseFloat(dat[4]);
                closingPrices.add(cPrice);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return closingPrices;
    }

    public static List<Float> readVolume(String symbol){
        String filepath = getFilepath(symbol);
        List<Float> closingPrices = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            br.readLine();
            String l;
            float vol;
            while ((l = br.readLine()) != null){
                String[] dat = l.split(",");
                vol = Float.parseFloat(dat[5]);
                closingPrices.add(vol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return closingPrices;
    }
    //days start at 0 and go to 99
    public static Float getClosePrice (int day, String symbol){
        String filepath = getFilepath(symbol);

        Float price = readClosingPrices(symbol).get(day);
        return price;
    }

    public static Float getVolume (int day, String symbol){
        String filepath = getFilepath(symbol);

        Float vol = readVolume(symbol).get(day);
        return vol;
    }
}
