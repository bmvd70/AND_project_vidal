package com.example.and_vidal.ui.profileface;

import android.os.Bundle;
import android.util.Log;
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

    private static final String TAG = "ProfileFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileFaceBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        FloatingActionButton fabProfile = binding.fabProfile;
        Button btnLogout = binding.btnLogout;
        mAuth = FirebaseAuth.getInstance();

        fabProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkLogin()) {
                    Toast.makeText(getContext(), "You are already logged in", Toast.LENGTH_SHORT).show();
                } else {
                    FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                    transaction.replace(R.id.profileContainer, new SignInFragment())
                            .addToBackStack(null)
                            .setReorderingAllowed(true)
                            .commit();
                }

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                checkLogin();
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

    @Override
    public void onResume() {
        super.onResume();
        checkLogin();
        Log.d(TAG, "onResume: in Profile Face");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: in Profile Face");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: in Profile Face");
    }

    boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            binding.textLoggedIn.setText(currentUser.getEmail());
            Toast.makeText(getActivity(), currentUser.getEmail() + " is signedin", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            binding.textLoggedIn.setText("No user");
            Toast.makeText(getActivity(), "No user", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}