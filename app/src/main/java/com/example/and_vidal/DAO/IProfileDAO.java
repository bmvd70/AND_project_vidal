package com.example.and_vidal.DAO;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

public interface IProfileDAO {

    void uploadProfilePicture(Uri uri);

    MutableLiveData<Uri> getProfilePicture();
}
