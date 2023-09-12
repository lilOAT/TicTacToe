package com.example.tictactoe;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class fragment_user_selection extends Fragment {

    //Declaring all actionable elements.
    EditText p1_name;
    EditText p2_name;
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

        int screenOrientation = getResources().getConfiguration().orientation;

        View rootView;

        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_user_selection_landscape, container,
                    false);
        } else {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_user_selection, container,
                    false);
        }

        // Introduce the Activity Data Store
        MainActivityData dataStore = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        //Linking to XML file.

        //Player Usernames
        p1_name = rootView.findViewById(R.id.profile1_name);
        p2_name = rootView.findViewById(R.id.profile2_name);

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
                if(dataStore.getUserSelection_profileToEdit() == 0 ||
                    dataStore.getUserSelection_profileToEdit() == 2)
                {
                    dataStore.setUserSelection_profileToEdit(1);
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
                if(dataStore.getUserSelection_profileToEdit() == 0 ||
                        dataStore.getUserSelection_profileToEdit() == 1)
                {
                    dataStore.setUserSelection_profileToEdit(2);
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
                if(dataStore.getUserSelection_profileToEdit() == 0) {
                    Toast toast = Toast.makeText(getContext(),
                            "Please select a Profile to edit!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    ArrayList<Player> list = dataStore.getPlayerList();
                    for(int i = 0; i < list.size(); i++) {
                        if(list.get(i).getName().equals(p1_name) ||
                                list.get(i).getName().equals(p2_name)) {
                            dataStore.setUserCustomization_profileID(i);
                        }
                    }
                    dataStore.setCurrentFrag(5);
                }
            }
        });

        //Logic for the Play Button.
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Checks whether a profile with the same name already exists, if it does, update
                //the image.
                int profileChecker = 0;

                // Check whether player 1 / player 2 are already in the table.
                ArrayList<Player> list = dataStore.getPlayerList();
                for(int i = 0; i < list.size(); i++) {
                    if (list.get(i).getName().equals(p1_name)) {
                        dataStore.getPlayerList().get(i).setAvatar((int)p1_button.getTag());
                        profileChecker++;
                    }
                    if (list.get(i).getName().equals(p2_name)) {
                        dataStore.getPlayerList().get(i).setAvatar((int)p2_button.getTag());
                    }
                }
                if(profileChecker == 0) {
                    dataStore.addToList(
                            new Player(
                                    p1_name.toString(),
                                    R.drawable.basic_profile_pic)
                    );
                }
            }
        });

        //Logic for the Back Button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Takes the user back to the Menu page.
                dataStore.setCurrentFrag(0);
            }
        });

        return rootView;
    }
}