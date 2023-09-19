package com.example.tictactoe;

import android.graphics.Bitmap;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainActivityData  extends ViewModel {

    private MutableLiveData<Integer> size; //Size of the board
    private MutableLiveData<Boolean> vsAI; //True if versing AI
    private MutableLiveData<Integer> winCondition; //Number in a row needed to win the game
    private MutableLiveData<Integer> player1Icon;
    private MutableLiveData<Integer> player2Icon;
    public MutableLiveData<Integer> currentFrag; //Represents current fragment displayed
    public MutableLiveData<ArrayList<Player>> playerList;
    private MutableLiveData<Hashtable<String, Bitmap>> imagesTable;
    private MutableLiveData<Integer> userSelection_profileToEdit;
    private MutableLiveData<Integer> userCustomization_profileID;
    private MutableLiveData<Player> player1, player2;
    private Player ai;

    public MainActivityData(){
        size = new MutableLiveData<>();
        vsAI = new MutableLiveData<>();
        winCondition = new MutableLiveData<>();
        player1Icon = new MutableLiveData<>();
        player2Icon = new MutableLiveData<>();
        userSelection_profileToEdit = new MutableLiveData<>();
        userCustomization_profileID = new MutableLiveData<>();
        currentFrag = new MutableLiveData<>();
        currentFrag.setValue(-1);
        player1 = new MutableLiveData<Player>();
        player2 = new MutableLiveData<Player>();
        ai = new Player("AI", "basic");

        //Set Default Values
        size.setValue(3);
        winCondition.setValue(3);
        player1Icon.setValue(R.drawable.cross);
        player2Icon.setValue(R.drawable.nought);
        userSelection_profileToEdit.setValue(0);
        userCustomization_profileID.setValue(-1);

        //Player Data
        ArrayList<Player> list = new ArrayList<Player>();
        playerList = new MutableLiveData<ArrayList<Player>>();
        playerList.setValue(list);
        list.add(ai);
        playerList.setValue(list);

        //Profile Images.
        imagesTable = new MutableLiveData<Hashtable<String, Bitmap>>();
        imagesTable.setValue(new Hashtable<String, Bitmap>());
    }

    //Getter Methods
    public int getSize(){
        return size.getValue();
    }
    public boolean getVsAI(){
        return vsAI.getValue();
    }
    public int getWinCondition(){
        return winCondition.getValue();
    }
    public int getPlayer1Icon(){return player1Icon.getValue();}
    public int getPlayer2Icon(){return player2Icon.getValue();}

    public Player getPlayer1(){return player1.getValue();}
    public Player getPlayer2(){return player2.getValue();}

    public Hashtable<String, Bitmap> getImagesList() {
        return imagesTable.getValue();
    }

    public int getUserSelection_profileToEdit() {
        return userSelection_profileToEdit.getValue();
    }
    public int getUserCustomization_profileID() {return userCustomization_profileID.getValue();}

    //Setter Methods
    public void setSize(int pSize){
        size.setValue(pSize);
    }
    public void setVsAI(boolean pAI){
        vsAI.setValue(pAI);
        setPlayer2(ai);
    }
    public void setWinCondition(int pCondition){
        winCondition.setValue(pCondition);
    }

    public void setPlayer1Icon(int p1IconRID) {
        player1Icon.setValue(p1IconRID);
    }
    public void setPlayer2Icon(int p2IconRID) {
        player2Icon.setValue(p2IconRID);
    }

    public void addProfileImage(String name, Bitmap newImage) {
        imagesTable.getValue().put(name, newImage);
    }

    public void setUserSelection_profileToEdit(int value) {
        userSelection_profileToEdit.setValue(value);
    }
    public void setUserCustomization_profileID(int value) {
        userCustomization_profileID.setValue(value);
    }

    public ArrayList<Player> getPlayerList() {
        return playerList.getValue();
    }

    public void addProfile(Player player) {
        playerList.getValue().add(player);
    }

    public void setPlayer1(Player player){player1.setValue(player);}
    public void setPlayer2(Player player){player2.setValue(player);}

    public int getCurrentFrag() {return currentFrag.getValue();}

    public void setCurrentFrag(int fragNum) {currentFrag.setValue(fragNum);}
}
