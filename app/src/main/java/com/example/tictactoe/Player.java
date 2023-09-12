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
    private int avatar;
    private int wins;
    private int losses;
    private int draws;

    public Player(String inName, int inAvatar) {
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
    public int getAvatar() {
        return avatar;
    }
    public void setAvatar(int inAvatar) {
        avatar = inAvatar;
    }
    public int getWins() {
        return wins;
    }
    public void incWins() {
        wins++;
    }
    public int getLosses() {
        return losses;
    }
    public void incLosses() {
        losses++;
    }
    public int getDraws() {
        return draws;
    }
    public void incDraws() {
        draws++;
    }
}
