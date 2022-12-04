package com.example.and_vidal.ui.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.and_vidal.ILoginHandler;
import com.example.and_vidal.LoginHandler;
import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentProfileBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private FragmentProfileBinding binding;
    private ILoginHandler loginHandler;
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;

    private FloatingActionButton fabProfile;
    private Button btnLogout;
    private TextView tvLoggedIn;
    private ImageView ivProfile;

    private static final String TAG = "ProfileFragment";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loginHandler = LoginHandler.getInstance();
        mAuth = loginHandler.getmAuth();
        sharedPreferences = requireActivity().getSharedPreferences("com.example.and_vidal", 0);

        ivProfile = binding.ivProfile;
        fabProfile = binding.fabProfile;
        btnLogout = binding.btnLogout;
        tvLoggedIn = binding.textLoggedIn;

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        // Get from db

        /*if (checkLogin() && Objects.requireNonNull(mAuth.getCurrentUser()).getPhotoUrl() != null) {
            //profileViewModel.getProfileImage().observe(getViewLifecycleOwner(), image -> {
            Uri image = mAuth.getCurrentUser().getPhotoUrl();
            if (image != null) {
                    ivProfile.setImageURI(image);
                    //uploadImage(image);
                }
            //});
        }*/

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), result -> {
                    Log.d(TAG, "Result: " + result.getResultCode());
                    Log.d(TAG, "Result: " + result.getData() + "\n");
                    Intent data = result.getData();
                    if (data != null) {
                        Uri selectedImage = data.getData();
                        if (selectedImage != null) {
                            ivProfile.setImageURI(selectedImage);
                            Log.d(TAG, "Image: " + selectedImage);
                            // temporary solution: save the uri as shared preference
                            sharedPreferences.edit().putString("profileImage_" + Objects.requireNonNull(mAuth.getCurrentUser()).getEmail(), selectedImage.toString()).apply();
                            Log.d(TAG, "Image: " + selectedImage.toString());
                            //uploadImage(selectedImage);
                            /*
                            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                    .setPhotoUri(Uri.parse(selectedImage.toString()))
                                    .build();
                            Objects.requireNonNull(mAuth.getCurrentUser()).updateProfile(profileUpdates)
                                    .addOnCompleteListener(task -> {
                                        if (task.isSuccessful()) {
                                            Log.d(TAG, "Profile Image updated.");
                                        }
                                    });*/
                        }
                    } else {
                        Log.d(TAG, "Intent: data is null");
                    }

        });

        fabProfile.setOnClickListener(v -> {
            if (checkLogin()) {
                // intent for selecting a picture from gallery
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);

            } else
                Navigation.findNavController(v).navigate(R.id.action_navigation_profile_to_navigation_signin);
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            checkLogin();
        });

        return root;
    }


    //private void uploadImage(Uri selectedImage) {
    //    profileViewModel.uploadProfilePicture(selectedImage);
    //}

    @SuppressLint("SetTextI18n")
    private boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            tvLoggedIn.setText(currentUser.getEmail());
            btnLogout.setEnabled(true);
            fabProfile.setImageResource(R.drawable.ic_baseline_image_search_24);
            if (sharedPreferences.getString("profileImage_" + currentUser.getEmail(), null) != null) {
                Log.d(TAG, "checkLogin: " + Uri.parse(sharedPreferences.getString("profileImage_" + currentUser.getEmail(), null)));
                //ivProfile.setImageURI(Uri.parse(sharedPreferences.getString("profileImage_" + currentUser.getEmail(), null)));
            }
            //if (currentUser.getPhotoUrl() != null) {
            //    ivProfile.setImageURI(currentUser.getPhotoUrl());
            //}
            return true;
        } else {
            tvLoggedIn.setText("No user");
            btnLogout.setEnabled(false);
            fabProfile.setImageResource(R.drawable.ic_baseline_login_24);
            ivProfile.setImageResource(R.drawable.ic_baseline_person_196);
            return false;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        checkLogin();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}