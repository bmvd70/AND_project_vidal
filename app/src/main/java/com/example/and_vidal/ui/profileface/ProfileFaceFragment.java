package com.example.and_vidal.ui.profileface;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentProfileFaceBinding;
import com.example.and_vidal.ui.signin.SignInFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileFaceFragment extends Fragment {

    private FragmentProfileFaceBinding binding;
    private FirebaseAuth mAuth;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileFaceViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileFaceViewModel.class);
        binding = FragmentProfileFaceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        NavController navController = NavHostFragment.findNavController(this);
        FloatingActionButton fabProfile = binding.fabProfile;
        Button btnLogout = binding.btnLogout;
        mAuth = FirebaseAuth.getInstance();

        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profileContainer, new SignInFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                checkLogin();
                Toast.makeText(getActivity(), "Users signed out", Toast.LENGTH_SHORT).show();
                /*FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.profileContainer, new SignInFragment())
                        .addToBackStack(null)
                        .setReorderingAllowed(true)
                        .commit();*/
            }
        });

        //profileViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkLogin();
    }

    void checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            binding.textLoggedIn.setText(currentUser.getEmail());
        } else {
            binding.textLoggedIn.setText("No user");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}