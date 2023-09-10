package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class fragment_menu extends Fragment {

    //Declaring all actionable elements.

    Button soloButton;
    Button versusButton;
    Button scoresButton;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_menu, container,
                false);

        //Linking to XML file.

        soloButton = rootView.findViewById(R.id.soloButton);
        versusButton = rootView.findViewById(R.id.versusButton);
        scoresButton = rootView.findViewById(R.id.scoresButton);

        // **********************************************

        //Logic for the Solo Button.
        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Logic for the Versus Button.
        versusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Logic for the Scores Button.
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Before loading ScoreboardFragment, sort the ArrayList<Player> by wins
            }
        });

        return rootView;
    }
}