package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class fragment_user_selection extends Fragment {

    //Declaring all actionable elements.
    Button p1_button;
    Button p2_button;
    Button editButton;
    Button playButton;
    Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_selection, container,
                false);

        // Introduce the Activity Data Store
        ActivityDataStore dataStore = new ViewModelProvider(getActivity()).
                get(ActivityDataStore.class);

        //Linking to XML file.

        //Player Avatars
        p1_button = rootView.findViewById(R.id.profile1);
        p2_button = rootView.findViewById(R.id.profile2);

        //Action Bar
        editButton = rootView.findViewById(R.id.edit_button);
        playButton = rootView.findViewById(R.id.play_button);
        backButton = rootView.findViewById(R.id.back_button);

        // **********************************************

        //Logic for P1's Button.
        p1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When selected, change the colour of the button's edge and set edit value to 1.
                if(dataStore.userSelection_profileToEdit.getValue() == 0 ||
                    dataStore.userSelection_profileToEdit.getValue() == 2)
                {
                    dataStore.userSelection_profileToEdit.setValue(1);
                    p1_button.setBackgroundResource(R.drawable.profile_edit_mode);
                    Toast toast = Toast.makeText(getContext(),
                            "Player 1 Selected!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Logic for P2's Button.
        p2_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When selected, change the colour of the button's edge and set edit value to 2.
                if(dataStore.userSelection_profileToEdit.getValue() == 0 ||
                        dataStore.userSelection_profileToEdit.getValue() == 1)
                {
                    dataStore.userSelection_profileToEdit.setValue(2);
                    p2_button.setBackgroundResource(R.drawable.profile_edit_mode);
                    Toast toast = Toast.makeText(getContext(),
                            "Player 2 Selected!", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        //Logic for the Edit Button.
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //First, check if a Profile has been selected to edit.
                if(dataStore.userSelection_profileToEdit.getValue() == 0) {
                    Toast toast = Toast.makeText(getContext(),
                            "Please select a Profile to edit!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    dataStore.userSelection_switcher.setValue(1);
                }
            }
        });

        //Logic for the Play Button.
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //Logic for the Back Button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}