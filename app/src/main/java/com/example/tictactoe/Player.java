package com.example.tictactoe;

/*
    - I think MutableLiveData is used, otherwise each instance won't be communicated between fragments??
    - Player() constructor
        Will Player instance be created when the "Play" button is created?
        If so then initiate with name and avatar
 */
public class Player {
    private String name;
    private String avatar;
    private int wins;
    private int losses;
    private int draws;
    private char playerCharacter;

    private int playerIconID;

    public Player(String inName, String inAvatar) {
        name = inName;
        avatar = inAvatar;
        wins = 0;
        losses = 0;
        draws = 0;
        playerCharacter = '*';
        playerIconID = R.drawable.cross;
    }

    public String getName() {
        return name;
    }
    public void setName(String inName) {
        name = inName;
    }
    public String getAvatar() {
        return avatar;
    }
    public void setAvatar(String inAvatar) {
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
    public int getPlayerIconID() {
        return playerIconID;
    }
    public void setPlayerIconID(int playerIconID) {
        this.playerIconID = playerIconID;
    }
}
