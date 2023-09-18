package com.example.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;


public class GameFragment extends Fragment {

    private boolean player1Turn;
    private int size;
    private int winCondition;
    private boolean vsAI;
    private ArrayList<Button> lastButtonTouched;
    private int p1IconID, p2IconID;
    private char[][] gameArray;

    private Player player1, player2;

    private TextView player1Name, player2Name;
    private int timer_in_seconds;
    private MutableLiveData<Integer> turnsTaken;
    private boolean timerRunning;
    private boolean gameOver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game,container,false);

        //Get access to data
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        //Set defaults
        player1Turn = true;
        lastButtonTouched = new ArrayList<>();
        turnsTaken = new MutableLiveData<>();
        turnsTaken.setValue(0);
        timerRunning = true;
        timer_in_seconds = 0;
        gameOver = false;

        //Get data from dataviewmodel
        size = mainActivityDataViewModel.getSize();
        winCondition = mainActivityDataViewModel.getWinCondition();
        vsAI = mainActivityDataViewModel.getVsAI();
        p1IconID = mainActivityDataViewModel.getPlayer1Icon();
        p2IconID = mainActivityDataViewModel.getPlayer2Icon();
        player1 = mainActivityDataViewModel.getPlayer1();
        player2 = mainActivityDataViewModel.getPlayer2();

        //Find and adjust xml placeholders
        player1Name = rootView.findViewById(R.id.player1);
        player1Name.setText(player1.getName());
        player2Name = rootView.findViewById(R.id.player2);
        player2Name.setText(player2.getName());
        player1Turn=true;
        TextView turnsTakenText = rootView.findViewById(R.id.turnsTakenText);

        TextView turnsAvailableText = rootView.findViewById(R.id.turnsAvailableText);
        TextView timerText = rootView.findViewById(R.id.timer);
        ImageButton pauseButton = rootView.findViewById(R.id.pauseButton);

        //Establish background game board
        gameArray = new char[size][size];
        for (char[] row: gameArray) {
            Arrays.fill(row,' ');
        }

        //Update if saved state exists
        if(savedInstanceState!=null){
            player1Turn = savedInstanceState.getBoolean("PLAYERTURN"); //Find out who's turn it currently is
            char[] game1Line = savedInstanceState.getCharArray("GAMEBOARD"); //Access the previous game board
            for (int i = 0; i < game1Line.length; i++) {
                gameArray[i/size][i%size] = game1Line[i];
            }

            timer_in_seconds = savedInstanceState.getInt("timeSec");
            timerRunning = savedInstanceState.getBoolean("timeRun");

            turnsTaken.setValue(savedInstanceState.getInt("turnsTakenInt"));
        }

        //Update player turn signals
        changeCurrentPlayer(player1Turn);
        timerText.setText(getTime(timer_in_seconds));
        turnsTakenText.setText("Turns: "+turnsTaken.getValue());
        turnsAvailableText.setText("Available: "+(size*size-turnsTaken.getValue()));

        //Begin creating grid of buttons
        TableLayout tableLayout = rootView.findViewById(R.id.tableLayout);

        //For every row, create a tableRow
        for (int i = 0; i < size; i++) {
            TableRow tableRow = new TableRow(getContext());

            //Create params for buttons within row
            TableRow.LayoutParams params = new TableRow.LayoutParams();
            params.weight=1;

            //For every column, create a button
            for (int j = 0; j < size; j++) {
                ResizeableButton button = new ResizeableButton(getContext());
                button.setId(i*size+j);
                button.setLayoutParams(params);

                int backgroundID;
                //Set button background based on game array
                switch (gameArray[i][j]){
                    case 'x':
                        backgroundID = p1IconID;
                        break;
                    case 'o':
                        backgroundID = p2IconID;
                        break;
                    default:
                        backgroundID = R.drawable.borderbox;
                }
                button.setBackgroundResource(backgroundID);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        //get button location from id
                        int row = button.getId()/size;
                        int col = button.getId()%size;

                        if(!timerRunning){//If game is paused, alert players and do not allow moves
                            gamePausedAlert(getActivity());
                        }
                        else{
                            if(gameArray[row][col]==' '){//Check if empty cell
                                if(player1Turn){//Assign background to number applicable
                                    button.setBackgroundResource(p1IconID);
                                    gameArray[row][col] = 'x';
                                    player1Turn = false;
                                    checkGameWin();

                                    if(vsAI&&!gameOver){//Run additional turn based on AI if AI selected
                                        int aiButtonID = aiTurn();

                                        gameArray[aiButtonID/size][aiButtonID%size] = 'o';

                                        Button aiButton = rootView.findViewById(aiButtonID);
                                        aiButton.setBackgroundResource(p2IconID);
                                        lastButtonTouched.add(aiButton);
                                        player1Turn = true;
                                        checkGameWin();
                                    }
                                }
                                else{
                                    button.setBackgroundResource(p2IconID); //Player 2 noughts
                                    gameArray[row][col] = 'o';
                                    player1Turn = true;
                                    checkGameWin();
                                }
                                changeCurrentPlayer(player1Turn); //Next players turn
                                lastButtonTouched.add(button); //Add last turn to list

                                //Update turns and available
                                turnsTaken.setValue(lastButtonTouched.size());
                            }
                            else{
                                //If player selects an invalid move, warn them
                                invalidMoveAlert(getActivity());
                            }
                        }
                    }
                });
                tableRow.addView(button); //Add button to table row
            }
            tableLayout.addView(tableRow); //Add table row to table
        }

        if(savedInstanceState!=null){//Must be done after button creation as buttons are not simple data types
            int[] buttonIDList = savedInstanceState.getIntArray("PREVTURNS");
            for (int i = 0; i < buttonIDList.length; i++) {
               Button button = rootView.findViewById(buttonIDList[i]);
               lastButtonTouched.add(button);
            }
        }

        Button undoButton = rootView.findViewById(R.id.undoButton);
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lastButtonTouched.isEmpty()){
                    undoMove();
                    if(vsAI){
                        undoMove();
                    }
                    turnsTaken.setValue(lastButtonTouched.size());
                }

            }
        });

        Button resetButton = rootView.findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!lastButtonTouched.isEmpty()) {
                    int timesToIterate = lastButtonTouched.size();
                    for (int i = 0; i < timesToIterate; i++) {
                        undoMove();
                    }
                    turnsTaken.setValue(lastButtonTouched.size());
                }
            }
        });

        //Observe when players make turns, update turns available and taken
        turnsTaken.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                turnsTakenText.setText("Turns: "+turnsTaken.getValue());
                turnsAvailableText.setText("Available: "+(size*size-turnsTaken.getValue()));
            }
        });

        //Establish chronometer for timer
        Chronometer chronometer = rootView.findViewById(R.id.chronometer);
        chronometer.start();

        //When chronometer ticks, if the timer is running, increase it
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(timerRunning){
                    timer_in_seconds++;
                    timerText.setText(getTime(timer_in_seconds));
                }
            }
        });

        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerRunning=!timerRunning;
                if(timerRunning){
                    pauseButton.setImageResource(R.drawable.pause);
                }
                else{
                    pauseButton.setImageResource(R.drawable.play);
                }
            }
        });

        return rootView;
    }

    private void checkGameWin(){
        if(WinChecker.checkWin(gameArray, size, winCondition)){
            if(player1Turn){
                player2.incWins();
                player1.incLosses();
                winAlert(player2.getName(),getActivity());
            }
            else{
                player1.incWins();
                player2.incLosses();
                winAlert(player1.getName(),getActivity());
            }
            gameOver = true;
        }
        if(!gameOver&&lastButtonTouched.size()+1==size*size){ //Check if max turns have been reached
            drawAlert(getActivity());
            player1.incDraws();
            player2.incDraws();
            gameOver = true;
        }
        else{ //Check for wins

        }
    }

    private void changeCurrentPlayer(boolean player1Turn){//Alternate player icons each turn
        if(player1Turn){
            player1Name.setBackgroundResource(R.drawable.borderbox);
            player2Name.setBackgroundResource(0);
        }
        else{
            player2Name.setBackgroundResource(R.drawable.borderbox);
            player1Name.setBackgroundResource(0);
        }
    }

    private int aiTurn(){
        ArrayList<Integer> validOptions = new ArrayList<>();

        //Check for all available options to play in.
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(gameArray[i][j]==' '){
                    validOptions.add(i*size+j);
                }
            }
        }
        //Randomly Select an option
        return validOptions.get((int)(Math.random()*validOptions.size()));
    }

    private void undoMove(){
        //Update board to new last turn
        Button lastButton = lastButtonTouched.get(lastButtonTouched.size()-1);
        //Reset last button background colour
        lastButton.setBackgroundResource(R.drawable.borderbox);
        int row = lastButton.getId()/size;
        int col = lastButton.getId()%size;
        //Reset last button in array
        gameArray[row][col] = ' ';

        //Remove undone button form list
        lastButtonTouched.remove(lastButton);

        //Update player turns
        player1Turn = !player1Turn;
        changeCurrentPlayer(player1Turn);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean("PLAYERTURN",player1Turn); //Save the players turn

        //Save game array in single row char array
        char[] gameIn1Line = new char[size*size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameIn1Line[count]=gameArray[i][j];
                count++;
            }
        }
        outState.putCharArray("GAMEBOARD",gameIn1Line);

        //Save previous turns taken
        int[] lastButtonTouchedInt = new int[lastButtonTouched.size()];
        for (int i = 0; i < lastButtonTouched.size(); i++) {
            lastButtonTouchedInt[i] = lastButtonTouched.get(i).getId();
        }
        outState.putIntArray("PREVTURNS", lastButtonTouchedInt);

        outState.putInt("timeSec",timer_in_seconds); //Save timer value
        outState.putBoolean("timeRun",timerRunning); //Save if timer is running
        outState.putInt("turnsTakenInt", turnsTaken.getValue());
    }

    //Translate time in seconds to clock for printing
    private String getTime(int timeInSeconds){
        int hoursInt = timeInSeconds/3600;
        int minutesInt = timeInSeconds/60;
        int secondsInt = timeInSeconds%60;
        return "Game time: "+parseTime(hoursInt)+":"+parseTime(minutesInt)+":"+parseTime(secondsInt);
    }

    private String parseTime(int time){
        if(time<10){
            return "0"+time;
        }
        else{
            return String.valueOf(time);
        }
    }

    private void winAlert(String winnerName, Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Game Over");
        alertDialog.setMessage(winnerName+" won!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Return to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                        get(MainActivityData.class);
                mainActivityDataViewModel.setCurrentFrag(0);
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    //Alert for if game is draw
    private void drawAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Game Over");
        alertDialog.setMessage("This game is a draw");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Return to Menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                        get(MainActivityData.class);
                mainActivityDataViewModel.setCurrentFrag(0);
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    //Alert for if player selects a taken square
    private void invalidMoveAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Invalid Move");
        alertDialog.setMessage("This square is already taken");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    private void gamePausedAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Game Paused");
        alertDialog.setMessage("The game is currently paused");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }
}