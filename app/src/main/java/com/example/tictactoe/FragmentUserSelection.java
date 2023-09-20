package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentUserSelection extends Fragment {

    //Declaring all actionable elements.
    TextView p1_name;
    TextView p2_name;
    ImageButton p1_button;
    ImageButton p2_button;
    Button playButton;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_user_selection, container,
                false);

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
        playButton = rootView.findViewById(R.id.play_button);

        //Checks whether we are vsing an AI.
        if(dataStore.getVsAI()) {
            if(dataStore.getPlayer2() != dataStore.getPlayerAI()) {
                dataStore.setPrevPlayer2();
            }
            dataStore.setPlayer2(dataStore.getPlayerAI());
            p2_name.setEnabled(false);
            p2_button.setEnabled(false);
        } else {
            if(dataStore.getPlayer2() == dataStore.getPlayerAI()) {
                dataStore.setPlayer2(dataStore.getPrevPlayer2());
            }
        }

        // Saved Instance.
        //Update if saved state exists
        if(savedInstanceState != null) {
            p1_name.setText(savedInstanceState.getString("p1_name"));
            p2_name.setText(savedInstanceState.getString("p2_name"));
        } else {
            p1_name.setText(dataStore.getPlayer1().getName());
            p2_name.setText(dataStore.getPlayer2().getName());
        }
        // **********************************************


        dataStore.currentFrag.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer currentFrag) {
                if(currentFrag == 1) {
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

                // Change fragment to user customization.
                dataStore.setCurrentFrag(5);
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

                //Change fragment to user customization,
                dataStore.setCurrentFrag(5);
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
                    if(p1_name.getText().toString().equals("")) {
                        for(Player player : dataStore.getPlayerList()) {
                            if(player.getName().equals("Guest 1")) {
                                dataStore.setPlayer1(player);
                            }
                        }
                    }

                    if(p2_name.getText().toString().equals("")) {
                        for(Player player : dataStore.getPlayerList()) {
                            if(player.getName().equals("Guest 2")) {
                                dataStore.setPlayer2(player);
                            }
                        }
                    }

                    if(dataStore.getPlayer1().getName().equals(dataStore.getPlayer2().getName())) {
                        Toast toast = Toast.makeText(getContext(),
                                "Player's cannot be the same!", Toast.LENGTH_LONG);
                        toast.show();

                        resetSelectionFragment(dataStore);
                    }
                    else {
                        // Continue with the game.
                        dataStore.setCurrentFrag(2);
                    }
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
        if(dataStore.getPlayer1() != null) {
            p1_name.setText(dataStore.getPlayer1().getName());
            p1_button.setImageBitmap(dataStore.getImagesList().get(dataStore.getPlayer1().getAvatar()));
        }
        if(dataStore.getPlayer2() != null) {
            p2_name.setText(dataStore.getPlayer2().getName());
            p2_button.setImageBitmap(dataStore.getImagesList().get(dataStore.getPlayer2().getAvatar()));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("p1_name", p1_name.getText().toString()); // Save profile 1 name.
        outState.putString("p2_name", p2_name.getText().toString()); // Save profile 2 name.
    }
}