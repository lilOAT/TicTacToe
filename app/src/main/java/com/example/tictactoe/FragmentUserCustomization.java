package com.example.tictactoe;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentUserCustomization extends Fragment {

    //Declaring all actionable elements.
    //Profile Name.
    TextView profileName;

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

    //Back Button.
    Button backButton;

    //Profile picture number.
    private String profilePicName = "";

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
            rootView = inflater.inflate(R.layout.fragment_user_customization_landscape, container,
                    false);
        } else {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_user_customization, container,
                    false);
        }

        // Introduce the Activity Data Store
        MainActivityData dataStore = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        //Linking to XML file.
        //Profile Name.
        profileName = rootView.findViewById(R.id.profileName);
        profileName.setText(dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).getName());

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

        // Back Button
        backButton = rootView.findViewById(R.id.back_button);
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

        //First, checks the current editable player's data to see if there is already a selected avatar.
        //If yes, then that avatar's image will be pre-selected - else the basic profile will be selected.
        dataStore.currentFrag.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if(integer == 5) {
                    for(Player player : dataStore.getPlayerList()) {
                        if(player.getName().equals(profileName.getText().toString())) {
                            if(!player.getAvatar().equals("")) {
                                int i = 0;
                                for(String possibleAvatarName : possibleAvatars) {
                                    if(possibleAvatarName.equals(player.getAvatar())) {
                                        buttonList[i].setBackgroundResource(R.drawable.profile_edit_mode);
                                    }
                                }
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
                dataStore.getPlayerList().
                        get(dataStore.getUserCustomization_profileID()).
                        setName(profileName.getText().toString());
                dataStore.getPlayerList().
                        get(dataStore.getUserCustomization_profileID()).
                        setAvatar(profilePicName);
                Toast toast = Toast.makeText(getContext(),
                        "Saved", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //Logic for Back Button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Takes user back to the user selection screen.
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

}