package com.example.and_vidal.ui.profile;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.and_vidal.ILoginHandler;
import com.example.and_vidal.LoginHandler;
import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentProfileBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private ILoginHandler loginHandler;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        loginHandler = LoginHandler.getInstance();
        FloatingActionButton fabProfile = binding.fabProfile;
        Button btnLogout = binding.btnLogout;
        mAuth = loginHandler.getmAuth();

        fabProfile.setOnClickListener(v -> {
            if (checkLogin()) {
                // TODO: intent for selecting a picture from gallery

            } else
                Navigation.findNavController(v).navigate(R.id.action_navigation_profile_to_navigation_signin);
        });

        btnLogout.setOnClickListener(v -> {
            mAuth.signOut();
            checkLogin();
        });

        return root;
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

    @SuppressLint("SetTextI18n")
    private boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            binding.textLoggedIn.setText(currentUser.getEmail());
            binding.btnLogout.setEnabled(true);
            binding.fabProfile.setImageResource(R.drawable.ic_baseline_image_search_24);
            return true;
        } else {
            binding.textLoggedIn.setText("No user");
            binding.btnLogout.setEnabled(false);
            binding.fabProfile.setImageResource(R.drawable.ic_baseline_login_24);
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}