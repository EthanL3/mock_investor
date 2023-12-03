package com.example.mockinvestor;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    public static List<Float> readClosingPrices(String filepath){
        List<Float> closingPrices = new ArrayList<>();

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

    public static List<Float> readVolume(String filepath){
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

}
