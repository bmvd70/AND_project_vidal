package com.example.and_vidal.ui.profile;

import android.net.Uri;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.and_vidal.repo.ProfileRepository;

public class ProfileViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final ProfileRepository repository;

    public ProfileViewModel() {
        repository = ProfileRepository.getInstance();
        mText = new MutableLiveData<>();
        mText.setValue("This is profile fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<Uri> getProfileImage() {
        return repository.getProfilePicture();
    }

    public void uploadProfilePicture(Uri uri) {
        repository.uploadProfilePicture(uri);
    }
}