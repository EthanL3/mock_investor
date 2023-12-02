package com.example.mockinvestor;
import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import android.util.Log;

import java.io.File;


public class avApi {

    public void stockDataUpdate(String symbol) {
        new StockDataAsyncTask().execute(symbol);
    }

    private Context context;

    private class StockDataAsyncTask extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            String apiKey = "CBP69ER5QF1IGEW5";
            String function = "TIME_SERIES_DAILY";
            String symbol = params[0];
            String base_url = "https://www.alphavantage.co/query";
            String apiUrl = base_url + "?function=" + function + "&symbol=" + symbol + "&apikey=" + apiKey;

            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                if (connection.getResponseCode() == 200) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    // Parse the JSON response and extract data
                    String csvData = parseJsonResponse(response.toString());

                    // Save the data to a CSV file
                    CSVWriter.writeStringToCSV(context, csvData, "historical_stock_data.csv");

                    System.out.println("Data saved to historical_stock_data.csv");
                    //print the path to the historical_stock_data.csv file




                } else {
                    System.out.println("Error: " + connection.getResponseCode());
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        private String parseJsonResponse(String jsonResponse) {
            // Parse JSON and extract data
            StringBuilder csvData = new StringBuilder("Date,Open,High,Low,Close,Volume\n");
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse);
                JSONObject timeSeries = jsonObject.getJSONObject("Time Series (Daily)");

                // Get the dates (keys) from the time series
                JSONArray dates = timeSeries.names();

                if (dates != null) {
                    // Iterate through each date
                    for (int i = 0; i < dates.length(); i++) {
                        String date = dates.getString(i);
                        JSONObject dailyData = timeSeries.getJSONObject(date);

                        String open = dailyData.getString("1. open");
                        String high = dailyData.getString("2. high");
                        String low = dailyData.getString("3. low");
                        String close = dailyData.getString("4. close");
                        String volume = dailyData.getString("5. volume");

                        csvData.append(date).append(",").append(open).append(",").append(high).append(",").append(low)
                                .append(",").append(close).append(",").append(volume).append("\n");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return csvData.toString();
        }

    }
    }
