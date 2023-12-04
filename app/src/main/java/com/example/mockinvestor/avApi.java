package com.example.mockinvestor;

import android.content.Context;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import android.util.Log;
import java.util.Properties;

import java.io.File;


public class avApi {

    public interface UpdateCallback {
        void onUpdateComplete();
        void onUpdateFailed();
    }
    //this will fetch the api key for the gradle.properties file
    private static final String PROPERTIES_FILE = "config.properties";
    private static final String API_KEY_PROPERTY = "ALPHA_VANTAGE_API_KEY";

    private String getApiKey(Context context) {
        try (InputStream input = context.getAssets().open(PROPERTIES_FILE)) {
            Properties properties = new Properties();
            properties.load(input);
            return properties.getProperty(API_KEY_PROPERTY);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stockDataUpdate(Context context, String symbol){

        String apiKey = getApiKey(context);

        new StockDataAsyncTask(context, apiKey).execute(symbol);
    }
    private Context context;

    private static class StockDataAsyncTask extends AsyncTask<String, Void, Void> {
        private Context context;
        private String apiKey;

        public StockDataAsyncTask(Context context, String apiKey) {
            this.context = context;
            this.apiKey = apiKey;
        }
        @Override
        protected Void doInBackground(String... params) {
            //String apiKey = BuildConfig.ALPHA_VANTAGE_API_KEY;
            String function = "TIME_SERIES_DAILY";
            String symbol = params[0];
            String base_url = "https://www.alphavantage.co/query";
            String apiUrl = base_url + "?function=" + function + "&symbol=" + symbol + "&apikey=" + apiKey;
            System.out.println(apiUrl);
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


                    String csvData = parseJsonResponse(response.toString());

                    //use the CSVWriter class to write the data to a csv file
                    String csvFileName = symbol + "_historical_stock_data.csv";

                    CSVWriter.writeStringToCSV(context, csvData, csvFileName);

                    System.out.println("Data saved to " + csvFileName);

                    //print the path to the historical_stock_data.csv file
                    File csvDirectory = new File(context.getFilesDir(), "CSVFiles");
                    if (!csvDirectory.exists()) {
                        csvDirectory.mkdirs();
                    }



                    File csvFile = new File(csvDirectory, csvFileName);
                    Log.d("Path to CSV file", csvFile.getAbsolutePath());



                } else {
                    Log.e("StockDataAsyncTask", "Error: " + connection.getResponseCode());
                }

                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("StockDataAsyncTask", "Error during API request or CSV creation: " + e.getMessage());

            }

            return null;
        }

        //method to process the json response from the api and return a string that can be converted to csv
        private String parseJsonResponse(String jsonResponse) {

            StringBuilder csvData = new StringBuilder("Date,Open,High,Low,Close,Volume\n");
            try {
                JSONObject jsonObject = new JSONObject(jsonResponse);
                Log.d("JSON Response", jsonObject.toString());
                JSONObject timeSeries = jsonObject.getJSONObject("Time Series (Daily)");


                JSONArray dates = timeSeries.names();

                if (dates != null) {

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
    public void runStockDataUpdateWithRetry(Context context, String symbol, int maxRetries, UpdateCallback callback) {
        int retryCount = 0;
        boolean csvFileCreated = false;

        while (retryCount < maxRetries && !csvFileCreated) {
            try {
                stockDataUpdate(context, symbol);
                csvFileCreated = true;
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("avApi", "Error during stock data update: " + e.getMessage());

                // Delete the CSV file if it exists to avoid issues with incomplete or corrupted files
                String csvFileName = symbol + "_historical_stock_data.csv";
                File csvFile = new File(context.getFilesDir() + File.separator + "CSVFiles", csvFileName);
                if (csvFile.exists()) {
                    csvFile.delete();
                    Log.d("avApi", "Deleted incomplete CSV file.");
                }
            }

            retryCount++;

            try {
                Thread.sleep(1000); // Sleep for 1 second before retrying
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (!csvFileCreated) {
            Log.e("avApi", "Max retries reached. CSV file not created.");
            callback.onUpdateFailed();
        } else {
            Log.d("avApi", "CSV file created successfully.");
            callback.onUpdateComplete();
        }

    }
}
