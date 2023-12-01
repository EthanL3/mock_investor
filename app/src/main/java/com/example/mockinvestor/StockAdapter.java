package com.example.mockinvestor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<ViewHolder>{

    Context context;
    ArrayList<Stock> stocks = new ArrayList<>();

    public StockAdapter(Context context, ArrayList<Stock> stocks) {
        this.context = context;
        this.stocks = stocks;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.stocks_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameView.setText(stocks.get(position).getName());
        holder.sharesView.setText(stocks.get(position).getShares());
        holder.valueView.setText(stocks.get(position).getValue());
        holder.gainsView.setText(stocks.get(position).getGains());

    }

    @Override
    public int getItemCount() {
        return stocks.size();
    }
}
