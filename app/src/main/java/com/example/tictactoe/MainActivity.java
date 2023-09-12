package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MenuFragment menuFragment = new MenuFragment(); // 0
    UserSelectionFragment userSelectionFragment = new UserSelectionFragment(); // 1
    GameFragment gameFragment = new GameFragment(); // 2
    SettingsFragment settingsFragment = new SettingsFragment(); // 3
    ScoreboardFragment scoreboardFragment = new ScoreboardFragment(); // 4
    UserCustomizationFragment userCustomizationFragment = new UserCustomizationFragment(); // 5

    private static final int MENUPAGE = 0;

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

        mainActivityDataViewModel.currentFrag.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // Menu Frag
                if (mainActivityDataViewModel.getCurrentFrag() == 0) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
                // User Select Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 1) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
                // Game Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 2) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
                // Settings Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 3) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
                // Scores Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 4) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
                // Avatar Select Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 5) {
                    //TODO
                    fm.beginTransaction().replace(R.id.);
                }
            }
        });

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