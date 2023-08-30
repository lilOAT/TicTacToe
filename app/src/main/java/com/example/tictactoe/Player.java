package com.example.tictactoe;

import android.graphics.drawable.Drawable;

import androidx.lifecycle.MutableLiveData;

/*
    - I think MutableLiveData is used, otherwise each instance won't be communicated between fragments??
    - Player() constructor
        Will Player instance be created when the "Play" button is created?
        If so then initiate with name and avatar
 */
public class Player {
    private String name;
    private Drawable avatar;
    private int wins;
    private int losses;
    private int draws;

    public Player(String inName, Drawable inAvatar) {
        name = inName;
        avatar = inAvatar;
        wins = 0;
        losses = 0;
        draws = 0;
    }

    public String getName() {
        return name;
    }
    public void setName(String inName) {
        name = inName;
    }
    public Drawable getAvatar() {
        return avatar;
    }
    public void setAvatar(Drawable inAvatar) {
        avatar = inAvatar;
    }
    public int getWins() {
        return wins;
    }
    public void incWins() {
        wins++;
    }
    public void incLosses() {
        losses++;
    }
    public void incDraws() {
        draws++;
    }
}
