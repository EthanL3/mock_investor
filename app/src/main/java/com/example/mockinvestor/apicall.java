
package com.example.mockinvestor;


import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class apicall {

    private static final String TAG = "apicall";
    private static final String BASE_URL = "https://yahoo-finance127.p.rapidapi.com/search";

    String apiKey;
    OkHttpClient client;

    public apicall() {
        this.apiKey = System.getenv("YAHOO_FINANCE_API_KEY");
        this.client = new OkHttpClient();
    }

    public stock_obj fetchStockInfo(String symbol) {
        Request request = new Request.Builder()
                .url(BASE_URL + "/" + symbol)
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", "yahoo-finance127.p.rapidapi.com")
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                Gson gson = new Gson();
                stock_obj stock = gson.fromJson(response.body().string(), stock_obj.class);
                return stock;
            } else {
                Log.e(TAG, "Error in API call. Response code: " + response.code());
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException during API call", e);
        }

        return null;
    }
}

