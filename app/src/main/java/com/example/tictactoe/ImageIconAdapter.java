package com.example.tictactoe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ImageIconAdapter extends RecyclerView.Adapter<ImageIconVH> {
    ArrayList<Integer> data;

    OnIconClickListener onIconClickListener;
    private int selectedItemPos = 0;
    public ImageIconAdapter(ArrayList<Integer> data){
        this.data = data;
    }


    @NonNull
    @Override
    public ImageIconVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.icon_item_layout, parent, false);
        ImageIconVH imageIconVH = new ImageIconVH(view);

        return imageIconVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageIconVH holder, int position) {
        Integer iconID = data.get(position);
        int currPos = position;
        holder.iconBox.setImageResource(iconID);

        holder.iconBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedItemPos = currPos;
                notifyDataSetChanged();
            }
        });

        if(selectedItemPos==currPos){
            holder.iconBox.setColorFilter(R.color.light_green);
            onIconClickListener.onIconClickListener(iconID);
        }
        else{
            holder.iconBox.setColorFilter(null);
        }
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setOnIconClickListener(ImageIconAdapter.OnIconClickListener onIconClickListener){
        this.onIconClickListener = onIconClickListener;
    }

    public interface OnIconClickListener{
        void onIconClickListener(Integer iconID);
    }
}
