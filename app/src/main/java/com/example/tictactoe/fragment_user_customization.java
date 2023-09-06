package com.example.tictactoe;

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
import android.widget.Toast;

public class fragment_user_customization extends Fragment {

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

    //Profile Avatar Names.
    String profile1Name;
    String profile2Name;
    String profile3Name;
    String profile4Name;
    String profile5Name;
    String profile6Name;
    String profile7Name;
    String profile8Name;
    String profile9Name;

    //Save Button.
    Button saveButton;

    //Back Button.
    Button backButton;

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_user_customization, container,
                false);

        // Introduce the Activity Data Store
        ActivityDataStore dataStore = new ViewModelProvider(getActivity()).
                get(ActivityDataStore.class);

        //Linking to XML file.
        //Profile Name.
        profileName = rootView.findViewById(R.id.profileName);

        //Profile Avatars.
        profile1Image = rootView.findViewById(R.id.profile1Image);
        profile2Image = rootView.findViewById(R.id.profile2Image);
        profile3Image = rootView.findViewById(R.id.profile3Image);
        profile4Image = rootView.findViewById(R.id.profile4Image);
        profile5Image = rootView.findViewById(R.id.profile5Image);
        profile6Image = rootView.findViewById(R.id.profile6Image);
        profile7Image = rootView.findViewById(R.id.profile7Image);
        profile8Image = rootView.findViewById(R.id.profile8Image);
        profile9Image = rootView.findViewById(R.id.profile9Image);

        //Save Button
        saveButton = rootView.findViewById(R.id.save_button);

        // Back Button
        backButton = rootView.findViewById(R.id.back_button);
        // **********************************************

        //Adding in data from Main Data Store.
        profile1Name = dataStore.profileTable.getValue().get(0).getUsername();
        profile2Name = dataStore.profileTable.getValue().get(1).getUsername();
        profile3Name = dataStore.profileTable.getValue().get(2).getUsername();
        profile4Name = dataStore.profileTable.getValue().get(3).getUsername();
        profile5Name = dataStore.profileTable.getValue().get(4).getUsername();
        profile6Name = dataStore.profileTable.getValue().get(5).getUsername();
        profile7Name = dataStore.profileTable.getValue().get(6).getUsername();
        profile8Name = dataStore.profileTable.getValue().get(7).getUsername();
        profile9Name = dataStore.profileTable.getValue().get(8).getUsername();
        //************************************

        //Logic for Profile Avatar Buttons.
        profile1Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(1);
                profile1Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile1Name);
            }
        });
        profile2Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(2);
                profile2Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile2Name);
            }
        });
        profile3Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(3);
                profile3Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile3Name);
            }
        });
        profile4Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(4);
                profile4Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile4Name);
            }
        });
        profile5Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(5);
                profile5Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile5Name);
            }
        });
        profile6Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(6);
                profile6Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile6Name);
            }
        });
        profile7Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(7);
                profile7Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile7Name);
            }
        });
        profile8Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(8);
                profile8Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile8Name);
            }
        });
        profile9Image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataStore.userCustomization_profileID.setValue(9);
                profile9Image.setBackgroundResource(R.drawable.profile_edit_mode);
                profileName.setText(profile9Name);
            }
        });

        //Logic for Save Button.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Make sure that a profile has been selected.
                if(dataStore.userCustomization_profileID.getValue() == 0) {
                    Toast toast = Toast.makeText(getContext(),
                            "Please select a Profile to edit!", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    dataStore.profileTable.getValue()
                            .get(dataStore.userCustomization_profileID.getValue() - 1)
                            .setUsername(profileName.getText().toString());
                }
            }
        });

        //Logic for Back Button.
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return rootView;
    }
}