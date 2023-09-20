package com.example.tictactoe;

public class Player {
    private String name;
    private String avatar;
    private int wins;
    private int losses;
    private int draws;
    private char playerCharacter;

    public Player(String inName, String inAvatar) {
        name = inName;
        avatar = inAvatar;
        wins = 0;
        losses = 0;
        draws = 0;
        playerCharacter = '*';
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
    public char getPlayerCharacter() {return playerCharacter;}
    public void setPlayerCharacter(char newChar) {playerCharacter = newChar;}
}
