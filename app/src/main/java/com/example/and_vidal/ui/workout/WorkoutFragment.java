package com.example.and_vidal.ui.workout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.databinding.FragmentWorkoutBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class WorkoutFragment extends Fragment {

    private FragmentWorkoutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        WorkoutViewModel workoutViewModel =
                new ViewModelProvider(this).get(WorkoutViewModel.class);
        binding = FragmentWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fabHome = binding.fabHome;

        fabHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "FAB_home clicked", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView textView = binding.textHome;
        workoutViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}