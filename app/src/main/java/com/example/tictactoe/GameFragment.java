package com.example.tictactoe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_game,container,false);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);

        player1Turn = true;
        lastButtonTouched = new ArrayList<>();


        size = mainActivityDataViewModel.getSize();
        winCondition = mainActivityDataViewModel.getWinCondition();
        vsAI = mainActivityDataViewModel.getVsAI();
        p1IconID = mainActivityDataViewModel.getPlayer1Icon();
        p2IconID = mainActivityDataViewModel.getPlayer2Icon();
        player1 = mainActivityDataViewModel.getPlayer1();
        player2 = mainActivityDataViewModel.getPlayer2();

        player1Name = rootView.findViewById(R.id.player1);
        player1Name.setText(player1.getName());
        player2Name = rootView.findViewById(R.id.player2);
        player2Name.setText(player2.getName());
        player1Turn=true;



        gameArray = new char[size][size];
        for (char[] row: gameArray) {
            Arrays.fill(row,' ');
        }

        if(savedInstanceState!=null){
            player1Turn = savedInstanceState.getBoolean("PLAYERTURN");
            char[] game1Line = savedInstanceState.getCharArray("GAMEBOARD");
            for (int i = 0; i < game1Line.length; i++) {
                gameArray[i/size][i%size] = game1Line[i];
            }
            lastButtonTouched.addAll((ArrayList<Button>) savedInstanceState.getSerializable("PREVTURNS"));
            System.out.println(lastButtonTouched.size());
        }

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
                        int row = button.getId()/size;
                        int col = button.getId()%size;

                        if(gameArray[row][col]==' '){//Check if empty cell
                            if(player1Turn){
                                button.setBackgroundResource(p1IconID); //Player 1 cross
                                gameArray[row][col] = 'x';
                                player1Turn = false;

                                if(vsAI){
                                    int aiButtonID = aiTurn();

                                    gameArray[aiButtonID/size][aiButtonID%size] = 'o';

                                    Button aiButton = rootView.findViewById(aiButtonID);
                                    aiButton.setBackgroundResource(p2IconID);
                                    lastButtonTouched.add(aiButton);
                                    checkGameWin();
                                    player1Turn = true;
                                }
                            }
                            else{
                                button.setBackgroundResource(p2IconID); //Player 2 noughts
                                gameArray[row][col] = 'o';
                                player1Turn = true;
                            }
                            changeCurrentPlayer(player1Turn);
                            lastButtonTouched.add(button);
                            checkGameWin();

                            //Update turns and available
                            setTurnsTaken(lastButtonTouched.size(),mainActivityDataViewModel);
                        }
                        else{
                            Alerts.invalidMoveAlert(getActivity());
                        }
                    }
                });
                tableRow.addView(button);
            }
            tableLayout.addView(tableRow);
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
                    setTurnsTaken(lastButtonTouched.size(),mainActivityDataViewModel);
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
                    setTurnsTaken(lastButtonTouched.size(),mainActivityDataViewModel);
                }
            }
        });

        return rootView;
    }

    public void checkGameWin(){
        if(lastButtonTouched.size()==size*size){
            Alerts.drawAlert(getActivity());
        }
        else{
            for (char[] row: gameArray) {
                System.out.println(Arrays.toString(row));
            }
            if(lastButtonTouched.size()==size*size){
                Alerts.drawAlert(getActivity());
                player1.incDraws();
                player2.incDraws();
            }
            else{
                if(WinChecker.checkWin(gameArray, size, winCondition)){
                    if(player1Turn){
                        player2.incWins();
                        player1.incLosses();
                        Alerts.winAlert("Player 2",getActivity());
                    }
                    else{
                        player1.incWins();
                        player2.incLosses();
                        Alerts.winAlert("Player 1",getActivity());
                    }
                }
                else{
                    System.out.println("NO WIN YET");
                }
            }
        }
    }

    public void changeCurrentPlayer(boolean player1Turn){
        if(player1Turn){
            player1Name.setBackgroundResource(R.color.white);
            player2Name.setBackgroundResource(R.color.black);
        }
        else{
            player2Name.setBackgroundResource(R.color.white);
            player1Name.setBackgroundResource(R.color.black);
        }
    }

    public int aiTurn(){
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

    public void undoMove(){
        //Update board to new last turn
        for (char[] row: gameArray) {
            System.out.println(Arrays.toString(row));
        }
        Button lastButton = lastButtonTouched.get(lastButtonTouched.size()-1);
        lastButton.setBackgroundResource(R.drawable.borderbox);
        int row = lastButton.getId()/size;
        int col = lastButton.getId()%size;
        gameArray[row][col] = ' ';

        lastButtonTouched.remove(lastButton);
        player1Turn = !player1Turn;
        changeCurrentPlayer(player1Turn);

        for (char[] row1: gameArray) {
            System.out.println(Arrays.toString(row1));
        }
    }

    public void setTurnsTaken(int turns, MainActivityData mainActivityDataViewModel){
        mainActivityDataViewModel.setTurnsTaken(turns);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putBoolean("PLAYERTURN",player1Turn);

        char[] gameIn1Line = new char[size*size];
        int count = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                gameIn1Line[count]=gameArray[i][j];
                count++;
            }
        }
        outState.putCharArray("GAMEBOARD",gameIn1Line);

        outState.putSerializable("PREVTURNS", lastButtonTouched);
    }
}