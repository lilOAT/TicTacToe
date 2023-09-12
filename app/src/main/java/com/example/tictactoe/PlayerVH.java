package com.example.tictactoe;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerVH extends RecyclerView.ViewHolder{
    public ImageView avatar;
    public TextView name;
    public TextView wins;
    public TextView losses;
    public TextView draws;
    public PlayerVH(@NonNull View itemView) {
        super(itemView);
        avatar = itemView.findViewById(R.id.playerAvatar);
        name = itemView.findViewById(R.id.playerName);
        wins = itemView.findViewById(R.id.playerWins);
        losses = itemView.findViewById(R.id.playerLosses);
        draws = itemView.findViewById(R.id.playerDraws);
    }
}

