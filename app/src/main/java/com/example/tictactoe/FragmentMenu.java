package com.example.tictactoe;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentMenu extends Fragment {

    //Declaring all actionable elements.

    Button soloButton;
    Button versusButton;
    Button scoresButton;
    Button settingsButton;

    //TODO add settings button

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int screenOrientation = getResources().getConfiguration().orientation;

        View rootView;

        if (screenOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_menu_landscape, container,
                    false);
        } else {
            // Inflate the layout for this fragment
            rootView = inflater.inflate(R.layout.fragment_menu, container,
                    false);
        }

        //Linking MutableLiveData
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);


        //Linking to XML file.

        soloButton = rootView.findViewById(R.id.soloButton);
        versusButton = rootView.findViewById(R.id.versusButton);
        scoresButton = rootView.findViewById(R.id.scoresButton);
        settingsButton = rootView.findViewById(R.id.settingsButton);

        // **********************************************

        //Logic for the Solo Button.
        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO Update player selection menu to only allow 1 player to be selected vs AI
                mainActivityDataViewModel.setVsAI(true);
                mainActivityDataViewModel.currentFrag.setValue(1);
            }
        });

        //Logic for the Versus Button.
        versusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.setVsAI(false);
                mainActivityDataViewModel.currentFrag.setValue(1);
            }
        });

        //Logic for the Scores Button.
        scoresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.currentFrag.setValue(4);
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivityDataViewModel.currentFrag.setValue(3);
            }
        });

        return rootView;
    }
}