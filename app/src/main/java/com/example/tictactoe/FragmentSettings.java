package com.example.tictactoe;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentSettings extends Fragment {
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
    ImageButton swapLogo;

    //************************************

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_settings, container,
                    false);

        // Introduce the Activity Data Store
        MainActivityData dataStore = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        //Linking java to XML layout.

        //Board size.
        boardSize3 = rootView.findViewById(R.id.board_size_three);
        boardSize4 = rootView.findViewById(R.id.board_size_four);
        boardSize5 = rootView.findViewById(R.id.board_size_five);

        //Win conditions.
        winCondition3 = rootView.findViewById(R.id.win_condition_three);
        winCondition4 = rootView.findViewById(R.id.win_condition_four);
        winCondition5 = rootView.findViewById(R.id.win_condition_five);

        //Highlight button from current setting
        if(dataStore.getSize() == 3) {
            winCondition4.setEnabled(false);
            winCondition5.setEnabled(false);
            boardSize3.setBackgroundResource(R.color.button_blue);
            boardSize4.setBackgroundResource(R.color.paled_blue);
            boardSize5.setBackgroundResource(R.color.paled_blue);
        } else if(dataStore.getSize() == 4) {
            winCondition5.setEnabled(false);
            boardSize3.setBackgroundResource(R.color.paled_blue);
            boardSize4.setBackgroundResource(R.color.button_blue);
            boardSize5.setBackgroundResource(R.color.paled_blue);
        } else if(dataStore.getSize() == 5) {
            boardSize3.setBackgroundResource(R.color.paled_blue);
            boardSize4.setBackgroundResource(R.color.paled_blue);
            boardSize5.setBackgroundResource(R.color.button_blue);
        }

        //Highlight button from current setting
        if(dataStore.getWinCondition() == 3) {
            winCondition3.setBackgroundResource(R.color.button_blue);
            winCondition4.setBackgroundResource(R.color.paled_blue);
            winCondition5.setBackgroundResource(R.color.paled_blue);
        } else if(dataStore.getWinCondition() == 4) {
            winCondition3.setBackgroundResource(R.color.paled_blue);
            winCondition4.setBackgroundResource(R.color.button_blue);
            winCondition5.setBackgroundResource(R.color.paled_blue);
        } else if(dataStore.getWinCondition() == 5) {
            winCondition3.setBackgroundResource(R.color.paled_blue);
            winCondition4.setBackgroundResource(R.color.paled_blue);
            winCondition5.setBackgroundResource(R.color.button_blue);
        }

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
                dataStore.setSize(3);

                //Disable non-applicable win conditions.
                winCondition4.setEnabled(false);
                winCondition5.setEnabled(false);
                dataStore.setWinCondition(3);
                winCondition3.setBackgroundResource(R.color.button_blue);
                winCondition4.setBackgroundResource(R.color.paled_blue);
                winCondition5.setBackgroundResource(R.color.paled_blue);

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
                dataStore.setSize(4);

                //Disable non-applicable win conditions.
                winCondition4.setEnabled(true);
                winCondition5.setEnabled(false);
                if(dataStore.getWinCondition() == 5) {
                    dataStore.setWinCondition(4);
                    winCondition3.setBackgroundResource(R.color.paled_blue);
                    winCondition4.setBackgroundResource(R.color.button_blue);
                    winCondition5.setBackgroundResource(R.color.paled_blue);
                }

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
                dataStore.setSize(5);

                //Enable all win conditions
                winCondition4.setEnabled(true);
                winCondition5.setEnabled(true);

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
                dataStore.setWinCondition(3);

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
                dataStore.setWinCondition(4);

                //Check whether win conditions are available.
                if(!winCondition4.isEnabled()) {
                    Toast toast = Toast.makeText(getContext(),
                            "You cannot use this win condition!", Toast.LENGTH_SHORT);
                    toast.show();
                }

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
                dataStore.setWinCondition(5);

                //Check whether win conditions are available.
                if(!winCondition5.isEnabled()) {
                    Toast toast = Toast.makeText(getContext(),
                            "You cannot use this win condition!", Toast.LENGTH_SHORT);
                    toast.show();
                }

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

                    dataStore.getPlayer1().setPlayerCharacter('O');
                    dataStore.getPlayer2().setPlayerCharacter('X');
                }
                else if(logo_check.equals("O")) {
                    p1_logo.setText("X");
                    p1_logo.setBackgroundResource(R.color.light_red);

                    p2_logo.setText("O");
                    p2_logo.setBackgroundResource(R.color.light_green);

                    dataStore.getPlayer1().setPlayerCharacter('X');
                    dataStore.getPlayer2().setPlayerCharacter('O');
                }
            }
        });

        return rootView;
    }
}