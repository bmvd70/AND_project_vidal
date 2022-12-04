package com.example.and_vidal.repo;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.and_vidal.DAO.IProfileDAO;
import com.example.and_vidal.DAO.ProfileDAO;

public class ProfileRepository {
    private static ProfileRepository instance;
    private final IProfileDAO profileDAO;
    private static final String TAG = "ProfileRepository";

    private ProfileRepository() {
        profileDAO = ProfileDAO.getInstance();
    }

    public static ProfileRepository getInstance() {
        if (instance == null) {
            instance = new ProfileRepository();
        }
        return instance;
    }

    public void uploadProfilePicture(Uri uri) {
        profileDAO.uploadProfilePicture(uri);
    }

    public MutableLiveData<Uri> getProfilePicture() {
        return profileDAO.getProfilePicture();
    }

}
