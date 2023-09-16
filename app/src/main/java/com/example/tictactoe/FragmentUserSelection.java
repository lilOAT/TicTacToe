package com.example.tictactoe;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class FragmentUserSelection extends Fragment {

    //Declaring all actionable elements.
    EditText p1_name;
    EditText p2_name;
    ImageButton p1_button;
    ImageButton p2_button;
    Button editButton;
    Button playButton;

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

        // **********************************************

        // Checks to see whether player's info has been updated.
        dataStore.hasProfileUpdated.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean hasProfileUpdated) {
                if(hasProfileUpdated) {
                    if(dataStore.getUserSelection_profileToEdit() == 1) {
                        p1_button.setImageBitmap(
                                dataStore.getImagesList().get(dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).getAvatar())
                        );
                    }
                    else if(dataStore.getUserSelection_profileToEdit() == 2) {
                        p2_button.setImageBitmap(
                            dataStore.getImagesList().get(dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).getAvatar())
                        );
                    }
                    dataStore.setUserSelection_profileToEdit(0);
                    dataStore.setUserCustomization_profileID(-1);
                    dataStore.hasProfileUpdated.setValue(false);
                }
            }
        });

        dataStore.currentFrag.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 1) {
                    for(Player player : dataStore.getPlayerList()) {
                        if(player.getName().equals(p1_name.getText().toString())) {
                            p1_button.setImageBitmap(dataStore.getImagesList().get(player.getAvatar()));
                        }
                        else if(player.getName().equals(p2_name.getText().toString())) {
                            p2_button.setImageBitmap(dataStore.getImagesList().get(player.getAvatar()));
                        }
                    }
                }
            }
        });

        //Logic for P1's Button.
        p1_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataStore.getUserSelection_profileToEdit() == 2) {
                    p2_button.setBackgroundResource(R.drawable.profile_standby);
                }

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
                if(dataStore.getUserSelection_profileToEdit() == 1) {
                    p1_button.setBackgroundResource(R.drawable.profile_standby);
                }

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
                    int i = 0;
                    for(Player player : dataStore.getPlayerList()) {
                        if(player.getName().equals(p1_name.getText().toString()) ||
                                player.getName().equals(p2_name.getText().toString())) {
                            dataStore.setUserCustomization_profileID(i);
                        }
                        i++;
                    }

                    if(i == dataStore.getPlayerList().size()) {
                        if(dataStore.getUserSelection_profileToEdit() == 1) {
                            dataStore.addProfile(
                                    new Player(p1_name.getText().toString(), "basic")
                            );
                        }
                        else if(dataStore.getUserSelection_profileToEdit() == 2) {
                            dataStore.addProfile(
                                    new Player(p2_name.getText().toString(), "basic")
                            );
                        }
                        dataStore.setUserCustomization_profileID(dataStore.getPlayerList().size() - 1);
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
                        dataStore.getPlayerList().get(i).setAvatar("");
                        profileChecker++;
                    }
                    if (list.get(i).getName().equals(p2_name)) {
                        dataStore.getPlayerList().get(i).setAvatar("");
                    }
                }
                if(profileChecker == 0) {
                    dataStore.addProfile(
                            new Player(
                                    p1_name.toString(),
                                    "")
                    );
                }

                dataStore.setCurrentFrag(2);
            }
        });

        return rootView;
    }
}