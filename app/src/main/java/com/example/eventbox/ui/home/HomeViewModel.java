package com.example.eventbox.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.eventbox.EventModel;
import com.example.eventbox.MainActivity;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private List<EventModel> dbEvents;

    public HomeViewModel(MainActivity mainActivity) {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        if (!dbEvents.equals(mainActivity.getDbEvents())){
            dbEvents = mainActivity.getDbEvents();
        }


    }

    public LiveData<String> getText() {
        return mText;
    }
}