package com.example.tictactoe;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class ScoreboardFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);
        RecyclerView rv = view.findViewById(R.id.recView);

        // Get list of Players and image hash table
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);
        ArrayList<Player> playerList = mainActivityDataViewModel.getPlayerList();
        Hashtable<String, Bitmap> imageList = mainActivityDataViewModel.getImagesList();

        //Sort playerList by wins
        playerList.sort(Comparator.comparingInt(Player::getWins));
        Collections.reverse(playerList);

        // Apply data of each player to recyclerview using the PlayerAdapter
        // List of players -> PlayerAdapter -> PlayerVH -> Recycler View
        rv.setLayoutManager(new LinearLayoutManager(rv.getContext(), LinearLayoutManager.VERTICAL,false));
        PlayerAdapter adapter = new PlayerAdapter(playerList, imageList);
        rv.setAdapter(adapter);

        return view;
    }
}