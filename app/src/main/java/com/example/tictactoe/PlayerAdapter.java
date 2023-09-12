package com.example.tictactoe;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerVH> {

    ArrayList<Player> data;
    public PlayerAdapter(ArrayList<Player> data){
        this.data = data;
    }
    @NonNull
    @Override
    public PlayerVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.player_list_item_layout,parent,false);
        PlayerVH myDataVHolder = new PlayerVH(view);
        return myDataVHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerVH holder, int position) {
        Player singleData = data.get(position);
        holder.avatar.setImageResource(singleData.getAvatar());
        holder.name.setText(singleData.getName());
        holder.wins.setText(singleData.getWins());
        holder.losses.setText(singleData.getLosses());
        holder.draws.setText(singleData.getDraws());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
