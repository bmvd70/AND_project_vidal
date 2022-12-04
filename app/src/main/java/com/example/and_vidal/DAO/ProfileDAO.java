package com.example.and_vidal.DAO;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

public class ProfileDAO implements IProfileDAO {
    private static ProfileDAO instance;
    private static final String TAG = "ProfileDAO";

    private ProfileDAO() {
    }

    public static ProfileDAO getInstance() {
        if (instance == null) {
            instance = new ProfileDAO();
        }
        return instance;
    }

    public void uploadProfilePicture(Uri uri) {

    }

    public MutableLiveData<Uri> getProfilePicture() {

        return null;
    }
}
