package com.example.mockinvestor;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView nameView, sharesView, valueView, gainsView;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        sharesView = itemView.findViewById(R.id.shares);
        valueView = itemView.findViewById(R.id.value);
        gainsView = itemView.findViewById(R.id.gains);
    }
}
