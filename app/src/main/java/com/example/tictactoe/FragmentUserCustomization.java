package com.example.tictactoe;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

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

        // Importing the images from the list to the activity.

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

        //Logic for Profile Avatar Buttons.
        profile1Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile1Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "basic";
            }
        });
        profile2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile2Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "baseball";
            }
        });
        profile3Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile3Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "basketball";
            }
        });
        profile4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile4Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "bowlingball";
            }
        });
        profile5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile5Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "eightball";
            }
        });
        profile6Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile6Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "charpic";
            }
        });
        profile7Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile7Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "soccerball";
            }
        });
        profile8Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
                profile8Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profilePicName = "volleyball";
            }
        });
        profile9Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetOtherImages();
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
                dataStore.hasProfileUpdated.setValue(true);

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

    private void resetOtherImages() {
        ImageButton[] list = {
                profile1Image,
                profile2Image,
                profile3Image,
                profile4Image,
                profile5Image,
                profile6Image,
                profile7Image,
                profile8Image,
                profile9Image };

        for (ImageButton imageButton : list) {
            imageButton.setBackgroundResource(R.drawable.profile_standby);
        }
    }

}