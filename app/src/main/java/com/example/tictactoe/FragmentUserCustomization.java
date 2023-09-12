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

    //Back Button.
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

        // Back Button
        backButton = rootView.findViewById(R.id.back_button);
        // **********************************************

        //Logic for Profile Avatar Buttons.
        profile1Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile1Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile1Image.getTag());
            }
        });
        profile2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile2Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile2Image.getTag());
            }
        });
        profile3Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile3Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile3Image.getTag());
            }
        });
        profile4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile4Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile4Image.getTag());
            }
        });
        profile5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile5Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile5Image.getTag());
            }
        });
        profile6Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile6Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile6Image.getTag());
            }
        });
        profile7Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile7Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile7Image.getTag());
            }
        });
        profile8Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile8Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile8Image.getTag());
            }
        });
        profile9Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profile9Image.setBackgroundResource(R.drawable.profile_edit_mode);
                dataStore.getPlayerList().get(dataStore.getUserCustomization_profileID()).setAvatar((int)profile9Image.getTag());
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
}