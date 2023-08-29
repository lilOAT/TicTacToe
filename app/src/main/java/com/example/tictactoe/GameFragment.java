package com.example.tictactoe;

import android.os.Bundle;

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
    private ArrayList<Button> lastButtonTouched;

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

        TextView player1 = rootView.findViewById(R.id.player1);
        TextView player2 = rootView.findViewById(R.id.player2);

        int[][] gameArray = new int[size][size];
        for (int[] row: gameArray) {
            Arrays.fill(row,0);
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
                button.setBackgroundResource(R.drawable.borderbox);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int row = button.getId()/size;
                        int col = button.getId()%size;

                        if(gameArray[row][col]==0){//Check if empty cell
                            if(player1Turn){
                                button.setBackgroundResource(R.drawable.cross); //Player 1 cross
                                gameArray[row][col] = 1;
                                player1Turn = false;
                            }
                            else{
                                button.setBackgroundResource(R.drawable.nought); //Player 2 noughts
                                gameArray[row][col] = 2;
                                player1Turn = true;
                            }
                            changeCurrentPlayer(player1Turn, player1, player2);
                            lastButtonTouched.add(button);
                            checkGameWin(gameArray);

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
                    //Update board to new last turn
                    Button lastButton = lastButtonTouched.get(lastButtonTouched.size()-1);
                    lastButton.setBackgroundResource(R.drawable.borderbox);
                    int row = lastButton.getId()/size;
                    int col = lastButton.getId()%size;
                    gameArray[row][col] = 0;

                    lastButtonTouched.remove(lastButton);
                    player1Turn = !player1Turn;
                    changeCurrentPlayer(player1Turn, player1, player2);
                }
            }
        });

        return rootView;
    }

    public void checkGameWin(int[][] boardArray){
        //TODO Implement win logic
    }

    public void changeCurrentPlayer(boolean player1Turn, TextView player1, TextView player2){
        if(player1Turn){
            player1.setBackgroundResource(R.color.white);
            player2.setBackgroundResource(R.color.black);
        }
        else{
            player2.setBackgroundResource(R.color.white);
            player1.setBackgroundResource(R.color.black);
        }
    }

}