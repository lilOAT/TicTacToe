package com.example.tictactoe;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView;

        rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        //Linking MutableLiveData
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).
                get(MainActivityData.class);

        //Linking to XML file.
        soloButton = rootView.findViewById(R.id.soloButton);
        versusButton = rootView.findViewById(R.id.versusButton);
        scoresButton = rootView.findViewById(R.id.scoresButton);
        settingsButton = rootView.findViewById(R.id.settingsButton);

        // **********************************************
        // Button Listeners

        //Logic for the Solo Button.
        soloButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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