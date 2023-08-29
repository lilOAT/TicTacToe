package com.example.tictactoe;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityData  extends ViewModel {

    private MutableLiveData<Integer> size; //Size of the board
    private MutableLiveData<Boolean> vsAI; //True if versing AI
    private MutableLiveData<Integer> winCondition; //Number in a row needed to win the game

    public MainActivityData(){
        size = new MutableLiveData<Integer>();
        vsAI = new MutableLiveData<Boolean>();
        winCondition = new MutableLiveData<Integer>();
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

}
