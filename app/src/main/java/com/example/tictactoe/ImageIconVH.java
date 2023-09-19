package com.example.tictactoe;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageIconVH extends RecyclerView.ViewHolder {
    public ImageView iconBox;

    public ImageIconVH(@NonNull View itemView) {
        super(itemView);
        iconBox = itemView.findViewById(R.id.iconBox);
    }
}
