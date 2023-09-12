package com.example.tictactoe;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Alerts {
    //Alert for if player wins
    public static void winAlert(String winnerName, Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Game Over");
        alertDialog.setMessage(winnerName+" won!");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    //Alert for if game is draw
    public static void drawAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Game Over");
        alertDialog.setMessage("This game is a draw");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    //Alert for if player selects a taken square
    public static void invalidMoveAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Invalid Move");
        alertDialog.setMessage("This square is already taken");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog.show();
    }

    //Alert for if player selects the return to menu button
    public static void menuButtonAlert(Activity activity){
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle("Menu Button");
        alertDialog.setMessage("All game progress and settings will be reset");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                /*TODO set to main page
                mainActivityDataViewModel.setPageNumber(MAINMENU)
                 */
            }
        });
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Resume", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });
        alertDialog.show();
    }
}
