package com.example.tictactoe;

import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Hashtable;

public class ActivityDataStore extends ViewModel {
    //Data Values for User Selection Fragment.
    //Switches between Select screen and Customization screen.
    MutableLiveData<Integer> userSelection_switcher;
    MutableLiveData<Integer> userSelection_profileToEdit;

    MutableLiveData<Integer> userCustomization_profileID;

    //****************************************

    //Create hash table to store each user profile independently with id.
    MutableLiveData<Hashtable<Integer, UserProfile>> profileTable;

    //Mandatory public constructor.
    public ActivityDataStore(){
        //Link values to their appropriate data types.
        userSelection_switcher = new MediatorLiveData<>();
        userSelection_profileToEdit = new MediatorLiveData<>();
        userCustomization_profileID = new MediatorLiveData<>();
        profileTable = new MediatorLiveData<Hashtable<Integer, UserProfile>>();
        //*******************************************

        //Initialize values.
        userSelection_switcher.setValue(0);
        userSelection_profileToEdit.setValue(0);
        userCustomization_profileID.setValue(0);
        //*******************************************

        //Insert maximum number of blank profiles to Profile Table.
        for(int i = 0; i < 9; i++) {
            profileTable.getValue().put(i, new UserProfile());
        }
    }
}
