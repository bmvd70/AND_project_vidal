package com.example.and_vidal.ui.nutrition;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NutritionViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public NutritionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Feature nutrition\ncoming in next update");
    }

    public LiveData<String> getText() {
        return mText;
    }
}