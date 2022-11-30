package com.example.and_vidal.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentProfileFaceBinding;
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
                    //Navigation.findNavController(v).navigate(R.id.action_navigation_profile_to_signInFragment);

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

    boolean checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            binding.textLoggedIn.setText(currentUser.getEmail());
            Toast.makeText(getActivity(), currentUser.getEmail() + " is signed in", Toast.LENGTH_SHORT).show();
            binding.btnLogout.setEnabled(true);
            return true;
        } else {
            binding.textLoggedIn.setText("No user");
            binding.btnLogout.setEnabled(false);
            return false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}