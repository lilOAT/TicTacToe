package com.example.tictactoe;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivityData  extends ViewModel {

    private int size; //Size of the board
    private boolean vsAI; //True if versing AI
    private int winCondition; //Number in a row needed to win the game
    private int player1Icon, player2Icon;
    public MutableLiveData<Integer> currentFrag; //Represents current fragment displayed
    public ArrayList<Player> playerList;
    private Hashtable<String, Bitmap> imagesTable;
    private int userSelection_profileToEdit;
    private int userCustomization_profileID;
    private Player player1, player2, ai;

    public MainActivityData(){
        size = 3;
        vsAI = true;
        winCondition = 3;
        player1Icon = R.drawable.cross;
        player2Icon = R.drawable.nought;
        currentFrag = new MutableLiveData<>();
        currentFrag.setValue(-1);
        ai = new Player("AI", "basic");
        playerList = new ArrayList<>();

        //Set Default Values
        userSelection_profileToEdit = 0;
        userCustomization_profileID = -1;

        //Player Data
        playerList.add(ai);

        //Profile Images.
        imagesTable = new Hashtable<String, Bitmap>();
    }

    //Getter Methods
    public int getSize(){
        return size;
    }
    public boolean getVsAI(){
        return vsAI;
    }
    public int getWinCondition(){
        return winCondition;
    }
    public int getPlayer1Icon(){return player1Icon;}
    public int getPlayer2Icon(){return player2Icon;}

    public Player getPlayer1(){return player1;}
    public Player getPlayer2(){return player2;}

    public Hashtable<String, Bitmap> getImagesList() {
        return imagesTable;
    }

    public int getUserSelection_profileToEdit() {
        return userSelection_profileToEdit;
    }
    public int getUserCustomization_profileID() {return userCustomization_profileID;}

    //Setter Methods
    public void setSize(int pSize){
        size=pSize;
    }
    public void setVsAI(boolean pAI){
        vsAI = pAI;
        setPlayer2(ai);
    }
    public void setWinCondition(int pCondition){
        winCondition = pCondition;
    }

    public void setPlayer1Icon(int p1IconRID) {
        player1Icon = p1IconRID;
    }
    public void setPlayer2Icon(int p2IconRID) {
        player2Icon = p2IconRID;
    }

    public void addProfileImage(String name, Bitmap newImage) {
        imagesTable.put(name, newImage);
    }

    public void setUserSelection_profileToEdit(int value) {
        userSelection_profileToEdit = value;
    }
    public void setUserCustomization_profileID(int value) {
        userCustomization_profileID = value;
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void addProfile(Player player) {
        playerList.add(player);
    }

    public void setPlayer1(Player player){player1 = player;}
    public void setPlayer2(Player player){player2 = player;}

    public int getCurrentFrag() {return currentFrag.getValue();}

    public void setCurrentFrag(int fragNum) {currentFrag.setValue(fragNum);}
}
