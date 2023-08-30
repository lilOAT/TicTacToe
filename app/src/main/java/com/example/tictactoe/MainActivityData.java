package com.example.tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData  extends ViewModel {

    private MutableLiveData<Integer> size; //Size of the board
    private MutableLiveData<Boolean> vsAI; //True if versing AI
    private MutableLiveData<Integer> winCondition; //Number in a row needed to win the game
    private MutableLiveData<Integer> player1Icon;
    private MutableLiveData<Integer> player2Icon;

    public MainActivityData(){
        size = new MutableLiveData<>();
        vsAI = new MutableLiveData<>();
        winCondition = new MutableLiveData<>();
        player1Icon = new MutableLiveData<>();
        player2Icon = new MutableLiveData<>();

        //Set Default Values
        size.setValue(3);
        winCondition.setValue(3);
        player1Icon.setValue(R.drawable.cross);
        player2Icon.setValue(R.drawable.nought);
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
}
