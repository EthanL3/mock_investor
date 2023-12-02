package com.example.mockinvestor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StockAdapter extends RecyclerView.Adapter<StockAdapter.MyViewHolder>{
    Context context;
    ArrayList<Stock> stockList;
    private final RecyclerViewInterface recyclerViewInterface;
    public StockAdapter(Context context, ArrayList<Stock> stockList, RecyclerViewInterface recyclerViewInterface) {
        this.stockList = stockList;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public StockAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row, parent, false);
        return new StockAdapter.MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull StockAdapter.MyViewHolder holder, int position) {
        holder.symbol.setText(stockList.get(position).getSymbol());
        holder.company_name.setText(stockList.get(position).getCompanyName());
        holder.quantity.setText(Double.toString(stockList.get(position).getQuantity()));
        holder.current_value.setText(Double.toString(stockList.get(position).getCurrentValue()));
        holder.gain_loss.setText(Double.toString(stockList.get(position).getGainLossDollars()));
    }

    @Override
    public int getItemCount() {
        return stockList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView symbol, company_name, quantity, current_value, gain_loss;
        public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            symbol = itemView.findViewById(R.id.symbol);
            company_name = itemView.findViewById(R.id.company_name);
            quantity = itemView.findViewById(R.id.quantity);
            current_value = itemView.findViewById(R.id.current_value);
            gain_loss = itemView.findViewById(R.id.gain_loss);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
