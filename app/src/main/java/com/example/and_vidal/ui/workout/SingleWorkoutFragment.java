package com.example.and_vidal.ui.workout;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.and_vidal.databinding.FragmentSingleWorkoutBinding;
import com.example.and_vidal.entities.WorkoutCategory;

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
        ImageButton btnBack = binding.btnBack;
        TextView tvWorkoutId = binding.singleWorkoutId;
        TextView tvWorkoutName = binding.singleWorkoutName;
        TextView tvWorkoutDescription = binding.singleWorkoutDescription;
        TextView tvWorkoutCategory = binding.singleWorkoutCategory;
        tvWorkoutDescription.setMovementMethod(new ScrollingMovementMethod());

        if (this.getArguments() != null) {
            Bundle bundle = this.getArguments();
            workoutId = bundle.getInt("workoutId");
            tvWorkoutId.setText(String.valueOf(workoutId));
            tvWorkoutName.setText(bundle.getString("workoutName"));
            tvWorkoutDescription.setText(bundle.getString("workoutDescription"));
            tvWorkoutCategory.setText(WorkoutCategory.getName(bundle.getInt("workoutCategory")));
            Log.d(TAG, "onCreateView: " + workoutId);
        }

        btnBack.setOnClickListener(v -> requireActivity().onBackPressed());

        tvWorkoutId.setText(String.valueOf(workoutId));

        Log.d(TAG, "onCreateView: " + workoutId);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}