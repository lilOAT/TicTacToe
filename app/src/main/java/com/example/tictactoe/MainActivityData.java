package com.example.tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainActivityData  extends ViewModel {

    private MutableLiveData<Integer> size; //Size of the board
    private MutableLiveData<Boolean> vsAI; //True if versing AI
    private MutableLiveData<Integer> winCondition; //Number in a row needed to win the game
    private MutableLiveData<Integer> player1Icon;
    private MutableLiveData<Integer> player2Icon;
    public MutableLiveData<ArrayList<Player>> playerList;
    MutableLiveData<Integer> userSelection_switcher;
    MutableLiveData<Integer> userSelection_profileToEdit;
    MutableLiveData<Integer> userCustomization_profileID;

    public MutableLiveData<Integer> turnsTaken;

    private MutableLiveData<Player> player1, player2;

    public MainActivityData(){
        size = new MutableLiveData<>();
        vsAI = new MutableLiveData<>();
        winCondition = new MutableLiveData<>();
        player1Icon = new MutableLiveData<>();
        player2Icon = new MutableLiveData<>();
        turnsTaken = new MutableLiveData<>();
        userSelection_switcher = new MutableLiveData<>();
        userSelection_profileToEdit = new MutableLiveData<>();
        userCustomization_profileID = new MutableLiveData<>();

        //Set Default Values
        size.setValue(3);
        winCondition.setValue(3);
        player1Icon.setValue(R.drawable.cross);
        player2Icon.setValue(R.drawable.nought);
        turnsTaken.setValue(0);

        //Player Data
        playerList = new MutableLiveData<ArrayList<Player>>();
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
    public int getTurnsTaken(){return turnsTaken.getValue();}

    public Player getPlayer1(){return player1.getValue();}
    public Player getPlayer2(){return player2.getValue();}

    public int getUserSelection_switcher() {
        return userSelection_switcher.getValue();
    }
    public int getUserSelection_profileToEdit() {
        return userSelection_profileToEdit.getValue();
    }

    //Setter Methods
    public void setSize(int pSize){
        size.setValue(pSize);
    }
    public void setVsAI(boolean pAI){
        vsAI.setValue(pAI);
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

    public void setTurnsTaken(int turns){turnsTaken.setValue(turns);}

    public void setUserSelection_switcher(int value) {
        userSelection_switcher.setValue(value);
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

    public void addToList(Player player) {
        playerList.getValue().add(player);
    }

    public void setPlayer1(Player player){player1.setValue(player);}
    public void setPlayer2(Player player){player2.setValue(player);}


}
