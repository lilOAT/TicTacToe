package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class fragment_settings extends Fragment {
    //Declaring all actionable elements.

    //Board size.
    Button boardSize3;
    Button boardSize4;
    Button boardSize5;

    //Win conditions.
    Button winCondition3;
    Button winCondition4;
    Button winCondition5;

    //Character selection - logos.
    TextView p1_logo;
    TextView p2_logo;
    Button swapLogo;

    //Action bar - TODO.
    Button actionButton;

    //Variables to act as main data store.
    private int boardSize = 0;
    private int winCondition = 0;
    //************************************

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int screenOrientation = getResources().getConfiguration().orientation;

        View rootView;

        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_settings_landscape, container,
                    false);
        } else {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_settings, container,
                    false);
        }

        //Linking java to XML layout.

        //Board size.
        boardSize3 = rootView.findViewById(R.id.board_size_three);
        boardSize4 = rootView.findViewById(R.id.board_size_four);
        boardSize5 = rootView.findViewById(R.id.board_size_five);

        //Win conditions.
        winCondition3 = rootView.findViewById(R.id.win_condition_three);
        winCondition4 = rootView.findViewById(R.id.win_condition_four);
        winCondition5 = rootView.findViewById(R.id.win_condition_five);

        //Character selection - logos.
        p1_logo = rootView.findViewById(R.id.p1_logo);
        p2_logo = rootView.findViewById(R.id.p2_logo);
        swapLogo = rootView.findViewById(R.id.swap_button);

        // ******************************************************* //

        //Logic for the board size buttons.
        boardSize3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set board size to 3x3.
                boardSize = 3;

                //Set other two buttons to lae color to signify button pressed.
                boardSize3.setBackgroundResource(R.color.button_blue);
                boardSize4.setBackgroundResource(R.color.paled_blue);
                boardSize5.setBackgroundResource(R.color.paled_blue);
            }
        });
        boardSize4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set board size to 4x4.
                boardSize = 4;

                //Set other two buttons to lae color to signify button pressed.
                boardSize3.setBackgroundResource(R.color.paled_blue);
                boardSize4.setBackgroundResource(R.color.button_blue);
                boardSize5.setBackgroundResource(R.color.paled_blue);
            }
        });
        boardSize5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set board size to 5x5.
                boardSize = 5;

                //Set other two buttons to lae color to signify button pressed.
                boardSize3.setBackgroundResource(R.color.paled_blue);
                boardSize4.setBackgroundResource(R.color.paled_blue);
                boardSize5.setBackgroundResource(R.color.button_blue);
            }
        });

        //Logic for win conditions buttons.
        winCondition3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set win condition to 3 logo's in a row.
                winCondition = 3;

                //Set other two buttons to pale colour to signify pressed button.
                winCondition3.setBackgroundResource(R.color.button_blue);
                winCondition4.setBackgroundResource(R.color.paled_blue);
                winCondition5.setBackgroundResource(R.color.paled_blue);
            }
        });
        winCondition4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set win condition to 4 logo's in a row.
                winCondition = 4;

                //Set other two buttons to pale colour to signify pressed button.
                winCondition3.setBackgroundResource(R.color.paled_blue);
                winCondition4.setBackgroundResource(R.color.button_blue);
                winCondition5.setBackgroundResource(R.color.paled_blue);
            }
        });
        winCondition5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set win condition to 5 logo's in a row.
                winCondition = 5;

                //Set other two buttons to pale colour to signify pressed button.
                winCondition3.setBackgroundResource(R.color.paled_blue);
                winCondition4.setBackgroundResource(R.color.paled_blue);
                winCondition5.setBackgroundResource(R.color.button_blue);
            }
        });

        //Logic for swap button.
        swapLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Swap current logo for each player.

                //Get colour and character for P1 logo.
                String logo_check = p1_logo.getText().toString();

                if(logo_check.equals("X")) {
                    p1_logo.setText("O");
                    p1_logo.setBackgroundResource(R.color.light_green);

                    p2_logo.setText("X");
                    p2_logo.setBackgroundResource(R.color.light_red);
                }
                else if(logo_check.equals("O")) {
                    p1_logo.setText("X");
                    p1_logo.setBackgroundResource(R.color.light_red);

                    p2_logo.setText("O");
                    p2_logo.setBackgroundResource(R.color.light_green);
                }
            }
        });

        return rootView;
    }
}