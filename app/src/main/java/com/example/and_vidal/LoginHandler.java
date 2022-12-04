package com.example.and_vidal;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginHandler implements ILoginHandler {
    private static LoginHandler instance;
    private final FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    private LoginHandler() {
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
    }

    public static LoginHandler getInstance() {
        if (instance == null) {
            instance = new LoginHandler();
        }
        return instance;
    }

    public FirebaseAuth getmAuth() {
        return mAuth;
    }

    public FirebaseUser getCurrentUser() {
        currentUser = mAuth.getCurrentUser();
        return currentUser;
    }
}
