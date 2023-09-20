package com.example.tictactoe;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class FragmentUserCustomization extends Fragment {

    //Declaring all actionable elements.
    //Profile Name.
    EditText profileName;

    //Profile Avatar Images.
    ImageButton profile1Image;
    ImageButton profile2Image;
    ImageButton profile3Image;
    ImageButton profile4Image;
    ImageButton profile5Image;
    ImageButton profile6Image;
    ImageButton profile7Image;
    ImageButton profile8Image;
    ImageButton profile9Image;

    //Save Button.
    Button saveButton;

    //Profile picture name.
    private String profilePicName = "";

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_user_customization, container, false);

        // Introduce the Activity Data Store
        MainActivityData dataStore = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        //Linking to XML file.
        //Profile Name.
        profileName = rootView.findViewById(R.id.profileName);

        // Image Buttons.
        profile1Image = rootView.findViewById(R.id.profile1Image);
        profile2Image = rootView.findViewById(R.id.profile2Image);
        profile3Image = rootView.findViewById(R.id.profile3Image);
        profile4Image = rootView.findViewById(R.id.profile4Image);
        profile5Image = rootView.findViewById(R.id.profile5Image);
        profile6Image = rootView.findViewById(R.id.profile6Image);
        profile7Image = rootView.findViewById(R.id.profile7Image);
        profile8Image = rootView.findViewById(R.id.profile8Image);
        profile9Image = rootView.findViewById(R.id.profile9Image);

        // Save Button
        saveButton = rootView.findViewById(R.id.save_button);

        // **********************************************

        // Keeps a user's avatar selection updated.
        String[] possibleAvatars = {
                "basic",
                "baseball",
                "basketball",
                "bowlingball",
                "eightball",
                "charpic",
                "soccerball",
                "volleyball",
                "tennisball"
        };

        ImageButton[] buttonList = {
                profile1Image,
                profile2Image,
                profile3Image,
                profile4Image,
                profile5Image,
                profile6Image,
                profile7Image,
                profile8Image,
                profile9Image
        };

        // Saved Instance.
        //Update if saved state exists
        if(savedInstanceState != null) {
            profileName.setText(savedInstanceState.getString("name"));
            profilePicName = savedInstanceState.getString("profile_pic");

            for(int i = 0; i < possibleAvatars.length; i++) {
                if(possibleAvatars[i].equals(profilePicName)) {
                    resetOtherImages(buttonList);
                    buttonList[i].setBackgroundResource(R.drawable.profile_edit_mode);
                }
            }
        }

        //First, checks the current editable player's data to see if there is already a selected avatar.
        //If yes, then that avatar's image will be pre-selected - else the basic profile will be selected.
        dataStore.currentFrag.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 5) {
                    if(dataStore.getUserSelection_profileToEdit() == 1) {
                        if(dataStore.getPlayer1() != null) {
                            profileName.setText(dataStore.getPlayer1().getName());

                            int i = 0;
                            for(String avatarName : possibleAvatars) {
                                if(avatarName.equals(dataStore.getPlayer1().getAvatar())) {
                                    resetOtherImages(buttonList);
                                    buttonList[i].setBackgroundResource(R.drawable.profile_edit_mode);
                                }
                                i++;
                            }
                        }
                    }
                    else if(dataStore.getUserSelection_profileToEdit() == 2) {
                        if(dataStore.getPlayer2() != null) {
                            profileName.setText(dataStore.getPlayer2().getName());

                            int i = 0;
                            for(String avatarName : possibleAvatars) {
                                if(avatarName.equals(dataStore.getPlayer2().getAvatar())) {
                                    resetOtherImages(buttonList);
                                    buttonList[i].setBackgroundResource(R.drawable.profile_edit_mode);
                                }
                                i++;
                            }
                        }
                    }
                }
            }
        });

        //Logic for Profile Avatar Buttons.
        profile1Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile1Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "basic";
            }
        });
        profile2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile2Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "baseball";
            }
        });
        profile3Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile3Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "basketball";
            }
        });
        profile4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile4Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "bowlingball";
            }
        });
        profile5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile5Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "eightball";
            }
        });
        profile6Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile6Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "charpic";
            }
        });
        profile7Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile7Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "soccerball";
            }
        });
        profile8Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile8Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "volleyball";
            }
        });
        profile9Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages(buttonList);
                profile9Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "tennisball";
            }
        });

        //Logic for the Save Button.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.setUserCustomization_profileID(-1);

                int i = 0;
                //Check whether profile is is table.
                for(Player player : dataStore.getPlayerList()) {
                    if(player.getName().equals(profileName.getText().toString())) {
                        dataStore.setUserCustomization_profileID(i);
                        dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setName(profileName.getText().toString());
                        dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar(profilePicName);
                    }
                    i++;
                }

                //Profile doesn't exist in table yet.
                if(dataStore.getUserCustomization_profileID() == -1) {
                    if(profileName.getText().toString().equals("")) {
                        Toast toast = Toast.makeText(getContext(),
                                "Player's must have a name!", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    else {
                        dataStore.addProfile(
                                new Player(profileName.getText().toString(), profilePicName)
                        );
                        dataStore.setUserCustomization_profileID(dataStore.getPlayerList().size() - 1);
                    }
                }

                //Update who the current Player 1 / 2 is.
                if(dataStore.getUserCustomization_profileID() != -1) {
                    if(dataStore.getUserSelection_profileToEdit() == 1) {
                        dataStore.setPlayer1(dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()));
                    }
                    if(dataStore.getUserSelection_profileToEdit() == 2) {
                        dataStore.setPlayer2(dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()));
                    }
                }

                Toast toast = Toast.makeText(getContext(),
                        "Saved", Toast.LENGTH_SHORT);
                toast.show();

                dataStore.setCurrentFrag(1);
            }
        });

        return rootView;
    }

    private void resetOtherImages(ImageButton[] buttonList) {
       for (ImageButton imageButton : buttonList) {
            imageButton.setBackgroundResource(R.drawable.profile_standby);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putString("name", profileName.getText().toString());
        outState.putString("profile_pic", profilePicName); // Save selected profile pic.
    }
}