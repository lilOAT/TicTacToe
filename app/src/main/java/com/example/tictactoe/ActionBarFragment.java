package com.example.tictactoe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ActionBarFragment extends Fragment {
    int timer_in_seconds; //Current game time
    boolean timerRunning; //Shows if timer is currently running

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_action_bar,container,false);

        //Get access to live data and layout texts and buttons
        MainActivityData mainActivityDataViewModel = new ViewModelProvider(getActivity()).get(MainActivityData.class);
        TextView turnsTaken = rootView.findViewById(R.id.turnsTaken);
        TextView turnsAvailable = rootView.findViewById(R.id.turnsAvailable);
        TextView timerText = rootView.findViewById(R.id.timer);

        //Load savedInstanceState if exists
        if(savedInstanceState!=null){
            timer_in_seconds = savedInstanceState.getInt("timeSec");
            timerText.setText(getTime(timer_in_seconds));
            timerRunning = savedInstanceState.getBoolean("timeRun");
            turnsTaken.setText("Turns: "+mainActivityDataViewModel.getTurnsTaken());
            turnsAvailable.setText("Available: "+(mainActivityDataViewModel.getSize()*mainActivityDataViewModel.getSize()-mainActivityDataViewModel.getTurnsTaken()));
        }

        mainActivityDataViewModel.turnsTaken.observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                turnsTaken.setText("Turns: "+mainActivityDataViewModel.getTurnsTaken());
                turnsAvailable.setText("Available: "+(mainActivityDataViewModel.getSize()*mainActivityDataViewModel.getSize()-mainActivityDataViewModel.getTurnsTaken()));
            }
        });

        //Establish chronometer for timer
        Chronometer chronometer = rootView.findViewById(R.id.chronometer);
        chronometer.start();

        //When chronometer ticks, if the timer is running, increase it
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                if(timerRunning){
                    timer_in_seconds++;
                    timerText.setText(getTime(timer_in_seconds));
                }
            }
        });

        ImageButton pauseButton = rootView.findViewById(R.id.pauseButton);
        pauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerRunning=!timerRunning;
                //TODO Change button to play button
            }
        });

        return rootView;
    }

    //Save instance for rotation
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putInt("timeSec",timer_in_seconds);
        outState.putBoolean("timeRun",timerRunning);
    }

    //Translate time in seconds to clock for printing
    public String getTime(int timeInSeconds){
        int hours = timeInSeconds/3600;
        int minutes = timeInSeconds/60;
        int seconds = timeInSeconds%60;
        String timeDisplay = hours+":"+minutes+":"+seconds;
        return timeDisplay;
    }

}
