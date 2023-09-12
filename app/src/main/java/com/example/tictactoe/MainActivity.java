package com.example.tictactoe;




import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FragmentMenu menuFragment = new FragmentMenu(); // 0
    FragmentUserSelection userSelectionFragment = new FragmentUserSelection(); // 1
    GameFragment gameFragment = new GameFragment(); // 2
    FragmentSettings settingsFragment = new FragmentSettings(); // 3
    ScoreboardFragment scoreboardFragment = new ScoreboardFragment(); // 4
    FragmentUserCustomization userCustomizationFragment = new FragmentUserCustomization(); // 5

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView titleText = findViewById(R.id.title);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);

        //TODO DEBUGGING
        //The following method is to populate the player list with dummy players for debugging
            Player player1 = new Player("player1", getResources().getIdentifier("baseball", null,null));
            Player player2 = new Player("player2", getResources().getIdentifier("baseball", null,null));
            Player player3 = new Player("player3", getResources().getIdentifier("baseball", null,null));
            ArrayList<Player> list = new ArrayList<Player>();
            list.add(player1);
            list.add(player2);
            list.add(player3);
            mainActivityDataViewModel.playerList.setValue(list);
            //playerList.getValue().add(player1);
            //playerList.getValue().add(player2);
            //playerList.getValue().add(player3);

        //TODO Set fragment to main menu, call subsequent fragments from fragments.

        //TODO Let values be set from menu fragments
        mainActivityDataViewModel.setSize(3);
        mainActivityDataViewModel.setWinCondition(3);
        mainActivityDataViewModel.setVsAI(false);

        loadMenuFragment();
        titleText.setText("Main Menu");

        mainActivityDataViewModel.currentFrag.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                // Menu Frag
                if (mainActivityDataViewModel.getCurrentFrag() == 0) {
                    loadMenuFragment();
                    titleText.setText("Main Menu");
                }
                // User Select Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 1) {
                    loadUserSelectionFragment();
                    titleText.setText("Player Selection");
                }
                // Game Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 2) {
                    loadGameFragment();
                    titleText.setText("Game");
                }
                // Settings Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 3) {
                    loadSettingsFragment();
                    titleText.setText("Settings");
                }
                // Scores Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 4) {
                    loadScoreboardFragment();
                    titleText.setText("Scoreboard");
                }
                // User Customization Frag
                else if (mainActivityDataViewModel.getCurrentFrag() == 5) {
                    //TODO
                    loadUserCustomizationFragment();
                    titleText.setText("Player Customization");
                }
            }
        });

        Button menuButton = findViewById(R.id.menuButton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If current frag is the game board, presses menu button will alert user that game progress will be lost
                if(mainActivityDataViewModel.getCurrentFrag() == 2) {
                    Alerts.menuButtonAlert(MainActivity.this);
                }
                mainActivityDataViewModel.setCurrentFrag(0);
            }
        });
    }

    private void loadMenuFragment(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.mainFrameContainer);
        if(frag == null) {
            fm.beginTransaction().add(R.id.mainFrameContainer,menuFragment).commit();
        } else{
            fm.beginTransaction().replace(R.id.mainFrameContainer,menuFragment).commit();
        }
    }
    private void loadUserSelectionFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFrameContainer,userSelectionFragment).commit();
    }
    private void loadGameFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFrameContainer,gameFragment).commit();
    }
    private void loadSettingsFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFrameContainer,settingsFragment).commit();
    }
    private void loadScoreboardFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFrameContainer,scoreboardFragment).commit();
    }
    private void loadUserCustomizationFragment(){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainFrameContainer,userCustomizationFragment).commit();
    }
}