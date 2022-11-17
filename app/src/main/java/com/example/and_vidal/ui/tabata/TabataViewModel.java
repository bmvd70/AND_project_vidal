package com.example.and_vidal.ui.tabata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabataViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TabataViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tabata fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}