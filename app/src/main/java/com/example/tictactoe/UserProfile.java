package com.example.tictactoe;

public class UserProfile {
    //Private variables to store information unique to each profile.
    private String username = "";
    private String userPicture = "";
    private int numWins = 0;
    private int numLosses = 0;

    //Public getter methods to retrieve this information.
    public String getUsername() {
        return username;
    }
    public String getUserPicture() {
        return userPicture;
    }
    public int getNumWins() {
        return numWins;
    }
    public int getNumLosses() {
        return numLosses;
    }

    //Public setter methods to modify information.
    public void setUsername(String inUsername) {
        username = inUsername;
    }
    public void setUserPicture(String inUserPicture) {
        userPicture = inUserPicture;
    }
    public void incrementWins() {
        numWins++;
    }
    public void incrementLosses() {
        numLosses++;
    }
}
