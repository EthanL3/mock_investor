package com.example.mockinvestor;

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
    public static ArrayList<Float> readClosingPrices(String symbol){
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
        List<Float> closingVolume = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            br.readLine();
            String l;
            float vol;
            while ((l = br.readLine()) != null){
                String[] dat = l.split(",");
                vol = Float.parseFloat(dat[5]);
                closingVolume.add(vol);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return closingVolume;
    }

    public static List<String> readDate(String symbol){
        String filepath = getFilepath(symbol);
        ArrayList<String> dates = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(filepath))){
            br.readLine();
            String l;
            String d;
            while ((l = br.readLine()) != null){
                String[] dat = l.split(",");
                d =dat[0];
                dates.add(d);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return dates;
    }

    //days start at 0 and go to 99
    public static float getClosePrice (int day, String symbol){
        String filepath = getFilepath(symbol);

        float price = readClosingPrices(symbol).get(day);
        return price;
    }

    public static float getVolume (int day, String symbol){
        String filepath = getFilepath(symbol);

        float vol = readVolume(symbol).get(day);
        return vol;
    }

    public static String getDate (int day, String symbol){
        String filepath = getFilepath(symbol);

        String date = readDate(symbol).get(day);
        return date;
    }

}
