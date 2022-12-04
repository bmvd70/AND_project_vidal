package com.example.and_vidal;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public interface ILoginHandler {
    FirebaseUser getCurrentUser();
    FirebaseAuth getmAuth();
}
