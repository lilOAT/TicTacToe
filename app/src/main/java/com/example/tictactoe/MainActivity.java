package com.example.tictactoe;




import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;

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
        Button menuButton = findViewById(R.id.menuButton);

        MainActivityData mainActivityDataViewModel = new ViewModelProvider(this).get(MainActivityData.class);

        //Setting up the profile images list.
        Bitmap baseball = BitmapFactory.decodeResource(getResources(), R.drawable.baseball);
        Bitmap basic_profile_pic = BitmapFactory.decodeResource(getResources(), R.drawable.basic_profile_pic);
        Bitmap basketball = BitmapFactory.decodeResource(getResources(), R.drawable.basketball);
        Bitmap bowlingball = BitmapFactory.decodeResource(getResources(), R.drawable.bowlingball);
        Bitmap eightball = BitmapFactory.decodeResource(getResources(), R.drawable.eightball);
        Bitmap charpic = BitmapFactory.decodeResource(getResources(), R.drawable.charpic1);
        Bitmap soccerball = BitmapFactory.decodeResource(getResources(), R.drawable.soccerball);
        Bitmap tennisball = BitmapFactory.decodeResource(getResources(), R.drawable.tennisball);
        Bitmap volleyball = BitmapFactory.decodeResource(getResources(), R.drawable.volleyball);

        mainActivityDataViewModel.addProfileImage("baseball", baseball);
        mainActivityDataViewModel.addProfileImage("basic", basic_profile_pic);
        mainActivityDataViewModel.addProfileImage("basketball", basketball);
        mainActivityDataViewModel.addProfileImage("bowlingball", bowlingball);
        mainActivityDataViewModel.addProfileImage("eightball", eightball);
        mainActivityDataViewModel.addProfileImage("charpic", charpic);
        mainActivityDataViewModel.addProfileImage("soccerball", soccerball);
        mainActivityDataViewModel.addProfileImage("tennisball", tennisball);
        mainActivityDataViewModel.addProfileImage("volleyball", volleyball);

        //Loads menu fragment
        if(mainActivityDataViewModel.getCurrentFrag() == -1) {
            mainActivityDataViewModel.setCurrentFrag(0);
        }


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
                //Hide Menu button if at menu
                if(mainActivityDataViewModel.getCurrentFrag() == 0) {
                    findViewById(R.id.menuButton).setVisibility(View.INVISIBLE);
                } else {
                    findViewById(R.id.menuButton).setVisibility(View.VISIBLE);
                }
            }
        });

        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //If current frag is the game board, presses menu button will alert user that game progress will be lost
                if(mainActivityDataViewModel.getCurrentFrag() == 2) {
                    //Alert for if player selects the return to menu button
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Menu Button");
                    alertDialog.setMessage("All game progress and settings will be reset");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Return to Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            mainActivityDataViewModel.setCurrentFrag(0);
                        }
                    });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Resume Game", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            alertDialog.dismiss();
                        }
                    });
                    alertDialog.show();
                }
                else{
                    mainActivityDataViewModel.setCurrentFrag(0);
                }
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
        Fragment frag = fm.findFragmentById(R.id.mainFrameContainer);
        //Do no replace instances of gameFrag already running
        if(!(frag instanceof GameFragment)){
            fm.beginTransaction().replace(R.id.mainFrameContainer,gameFragment).commit();
        }
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