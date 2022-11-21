package com.example.and_vidal.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.FirebaseUIActivity;
import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentProfileBinding;
import com.example.and_vidal.ui.login.LoginFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab_profile = binding.fabProfile;

        fab_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getContext();

                Toast.makeText(getActivity(), "FAB_profile was clicked", Toast.LENGTH_SHORT).show();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.nav_host_fragment_activity_main, new LoginFragment()).commit();
            }
        });

        final TextView textView = binding.textProfile;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}