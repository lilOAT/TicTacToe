package com.example.tictactoe;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
                button.setBackgroundColor(R.color.white);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int row = (int)button.getId()/size;
                        int col = (int)button.getId()%size;

                        if(gameArray[row][col]==0){//Check if empty cell
                            if(player1Turn){
                                button.setBackgroundResource(R.drawable.cross); //Player 1 cross
                                gameArray[row][col] = 1;
                                player1Turn = false;
                                player1.setBackgroundColor(R.color.black);
                                player2.setBackgroundColor(R.color.white);
                            }
                            else{
                                button.setBackgroundResource(R.drawable.nought); //Player 2 noughts
                                gameArray[row][col] = 2;
                                player1Turn = true;
                                player1.setBackgroundColor(R.color.white);
                                player2.setBackgroundColor(R.color.black);
                            }
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
                    lastButton.setBackgroundColor(R.color.white);
                    int row = lastButton.getId()/size;
                    int col = lastButton.getId()%size;
                    gameArray[row][col] = 0;

                    lastButtonTouched.remove(lastButton);
                    player1Turn = !player1Turn;
                }
            }
        });

        return rootView;
    }

    public void checkGameWin(int[][] boardArray){
        //TODO Implement win logic
    }
}