package com.example.and_vidal.ui.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileFaceViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ProfileFaceViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is profileface fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}