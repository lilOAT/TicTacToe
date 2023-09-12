package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    GameFragment gameFragment = new GameFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);

        FragmentManager fm = getSupportFragmentManager();
        Fragment boardFrag = fm.findFragmentById(R.id.mainFrameContainer); //Set fragment to board frame.
        //TODO Set fragment to main menu, call subsequent fragments from fragments.

        //TODO Let values be set from menu fragments
        mainActivityDataViewModel.setSize(3);
        mainActivityDataViewModel.setWinCondition(3);
        mainActivityDataViewModel.setVsAI(false);

        if(boardFrag==null){
            fm.beginTransaction().add(R.id.mainFrameContainer,gameFragment).commit();
        }

        Button menuButton = findViewById(R.id.menuButton);
        TextView titleText = findViewById(R.id.title);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alerts.menuButtonAlert(MainActivity.this);
                titleText.setText("Main Menu");
            }
        });

    }
}