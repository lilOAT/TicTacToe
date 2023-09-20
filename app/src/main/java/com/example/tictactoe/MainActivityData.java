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
    public MutableLiveData<Integer> currentFrag; //Represents current fragment displayed
    public ArrayList<Player> playerList;
    private Hashtable<String, Bitmap> imagesTable;
    private int userSelection_profileToEdit;
    private int userCustomization_profileID;
    private Player player1, player2, prevPlayer2, ai;

    public MainActivityData(){
        size = 3;
        vsAI = true;
        winCondition = 3;
        currentFrag = new MutableLiveData<>();
        currentFrag.setValue(-1);
        ai = new Player("AI", "basic");
        ai.setPlayerIconID(R.drawable.android);
        playerList = new ArrayList<>();
        Player guest1 = new Player("Guest 1", "basic");
        Player guest2 = new Player("Guest 2", "basic");
        playerList.add(guest1);
        playerList.add(guest2);
        player1 = guest1;
        player2 = guest2;
        prevPlayer2 = player2;


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
    public Player getPlayer1(){return player1;}
    public Player getPlayer2(){return player2;}
    public Player getPlayerAI() {return ai;}
    public Player getPrevPlayer2() {return prevPlayer2;}
    public void setPlayer1(Player player){player1 = player;}
    public void setPlayer2(Player player){player2 = player;}
    public void setPrevPlayer2() {
        prevPlayer2 = player2;
    }

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
//        if(pAI) {
//            setPlayer2(ai);
//        }
    }
    public void setWinCondition(int pCondition){
        winCondition = pCondition;
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

    public int getCurrentFrag() {return currentFrag.getValue();}

    public void setCurrentFrag(int fragNum) {currentFrag.setValue(fragNum);}
}
