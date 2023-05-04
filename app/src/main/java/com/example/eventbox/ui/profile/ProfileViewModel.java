package com.example.eventbox.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<String> mName;

    public ProfileViewModel() {
        mName = new MutableLiveData<>();
    }

    public LiveData<String> getName() {
        return mName;
    }
}