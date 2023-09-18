package com.example.tictactoe;

import androidx.annotation.NonNull;
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

        // Saved Instance.
        //Update if saved state exists
        if(savedInstanceState != null) {
            p1_name.setText(savedInstanceState.getString("p1_name"));
            p2_name.setText(savedInstanceState.getString("p2_name"));
        }

        // Introduce the Activity Data Store
        MainActivityData dataStore = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        // Check screen orientation.
        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_user_selection_landscape, container,
                    false);
        } else {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_user_selection, container,
                    false);
        }

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

        dataStore.currentFrag.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 1) {
                    //Checks whether we are vsing an AI.
                    if(dataStore.getVsAI()) {
                        p2_name.setText("AI");
                        p2_name.setEnabled(false);
                        p2_button.setEnabled(false);
                    }
                    else {
                        p2_name.setText("");
                        p2_name.setEnabled(true);
                        p2_button.setEnabled(true);
                    }

                    //Reset profile to edit.
                    dataStore.setUserSelection_profileToEdit(0);

                    updateImageButtons(dataStore);
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
                dataStore.setUserSelection_profileToEdit(1);
                p1_button.setBackgroundResource(R.drawable.profile_edit_mode);
                Toast toast = Toast.makeText(getContext(),
                        "Player 1 Selected!", Toast.LENGTH_SHORT);
                toast.show();
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
                dataStore.setUserSelection_profileToEdit(2);
                p2_button.setBackgroundResource(R.drawable.profile_edit_mode);
                Toast toast = Toast.makeText(getContext(),
                        "Player 2 Selected!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Logic for the Edit Button.
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.setUserCustomization_profileID(-1);

                //First, check if a Profile has been selected to edit.
                if(dataStore.getUserSelection_profileToEdit() == 0) {
                    Toast toast = Toast.makeText(getContext(),
                            "Please select a Profile to edit!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    int i = 0;

                    //Check whether selected profile is is table.
                    if(dataStore.getUserSelection_profileToEdit() == 1) {
                        for(Player player : dataStore.getPlayerList()) {
                            if(player.getName().equals(p1_name.getText().toString())) {
                                dataStore.setUserCustomization_profileID(i);
                            }
                            i++;
                        }
                    }
                    else if(dataStore.getUserSelection_profileToEdit() == 2) {
                        for(Player player : dataStore.getPlayerList()) {
                            if(player.getName().equals(p2_name.getText().toString())) {
                                dataStore.setUserCustomization_profileID(i);
                            }
                            i++;
                        }
                    }

                    //Profile doesn't exist in table yet.
                    if(dataStore.getUserCustomization_profileID() == -1) {
                        if(dataStore.getUserSelection_profileToEdit() == 1) {
                            if(p1_name.getText().toString().equals("")) {
                                Toast toast = Toast.makeText(getContext(),
                                        "Player's must have a name!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else {
                                dataStore.addProfile(
                                        new Player(p1_name.getText().toString(), "basic")
                                );
                                dataStore.setUserCustomization_profileID(dataStore.getPlayerList().size() - 1);
                            }
                        }
                        else if(dataStore.getUserSelection_profileToEdit() == 2) {
                            if(p2_name.getText().toString().equals("")) {
                                Toast toast = Toast.makeText(getContext(),
                                        "Player's must have a name!", Toast.LENGTH_SHORT);
                                toast.show();
                            }
                            else {
                                dataStore.addProfile(
                                        new Player(p2_name.getText().toString(), "basic")
                                );
                                dataStore.setUserCustomization_profileID(dataStore.getPlayerList().size() - 1);
                            }
                        }
                    }

                    //If no errors have occurred, proceed with the customization.
                    if(dataStore.getUserCustomization_profileID() != -1) {
                        dataStore.setCurrentFrag(5);
                    }
                    //If something has gone wrong, reset all parameters and allow user to try again.
                    else {
                        resetSelectionFragment(dataStore);
                    }
                }
            }
        });

        //Logic for the Play Button.
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(p1_name.getText().toString().equals("") || p2_name.getText().toString().equals("")) {
                    Toast toast = Toast.makeText(getContext(),
                            "Player's must have a name!", Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    // Set active Player 1 and Player 2.
                    int i = 0;
                    for(Player player : dataStore.getPlayerList()) {
                        if(player.getName().equals(p1_name.getText().toString())) {
                            dataStore.setPlayer1(player);
                        }
                        if(player.getName().equals(p2_name.getText().toString())) {
                            dataStore.setPlayer2(player);
                        }
                        i++;
                    }
                    if(i == dataStore.getPlayerList().size()) {
                        if(dataStore.getPlayer1() == null) {
                            Player player = new Player(p1_name.getText().toString(), "basic");
                            dataStore.addProfile(player);
                            dataStore.setPlayer1(player);
                        }
                        else if(dataStore.getPlayer2() == null) {
                            Player player = new Player(p2_name.getText().toString(), "basic");
                            dataStore.addProfile(player);
                            dataStore.setPlayer2(player);
                        }
                    }

                    if(dataStore.getPlayer1().getName().equals(dataStore.getPlayer2().getName())) {
                        Toast toast = Toast.makeText(getContext(),
                                "Player's cannot be the same!", Toast.LENGTH_LONG);
                        toast.show();

                        resetSelectionFragment(dataStore);
                    }

                    // Continue with the game.
                    dataStore.setCurrentFrag(2);
                }
            }
        });

        return rootView;
    }

    private void resetSelectionFragment(MainActivityData dataStore) {
        p1_name.setText("");
        p2_name.setText("");
        dataStore.setUserSelection_profileToEdit(0);
        dataStore.setUserCustomization_profileID(-1);
        p1_button.setBackgroundResource(R.drawable.profile_standby);
        p2_button.setBackgroundResource(R.drawable.profile_standby);
    }

    private void updateImageButtons(MainActivityData dataStore) {
        //Retain the selected image for the player upon fragment loading.

        for (Player player : dataStore.getPlayerList()) {
            if (player.getName().equals(p1_name.getText().toString())) {
                p1_button.setImageBitmap(dataStore.getImagesList().get(player.getAvatar()));
            }
            if(player.getName().equals(p2_name.getText().toString())) {
                p2_button.setImageBitmap(dataStore.getImagesList().get(player.getAvatar()));
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("p1_name", p1_name.getText().toString()); // Save profile 1 name.
        outState.putString("p2_name", p2_name.getText().toString()); // Save profile 2 name.
    }
}