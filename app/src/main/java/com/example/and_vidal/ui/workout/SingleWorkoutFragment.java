package com.example.and_vidal.ui.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.and_vidal.R;
import com.example.and_vidal.databinding.FragmentSingleWorkoutBinding;

import java.util.Objects;

public class SingleWorkoutFragment extends Fragment {
    private static final String TAG = "SingleWorkoutFragment";
    private FragmentSingleWorkoutBinding binding;

    private int workoutId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSingleWorkoutBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ImageButton btnBack = (ImageButton) binding.btnBack;
        TextView tvWorkoutId = (TextView) binding.singleWorkoutId;

        if (this.getArguments() != null) {
            Bundle bundle = this.getArguments();
            workoutId = bundle.getInt("workoutId");
            Log.d(TAG, "onCreateView: " + workoutId);
        }

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        tvWorkoutId.setText(String.valueOf(workoutId));

        Log.d(TAG, "onCreateView: " + workoutId);
        return root;
    }
}