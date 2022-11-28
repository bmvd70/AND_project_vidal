package com.example.and_vidal.ui.singleworkout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SingleWorkoutViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public SingleWorkoutViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is singleWorkout fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}