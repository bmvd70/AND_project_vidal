package com.example.and_vidal.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.and_vidal.databinding.FragmentStatsBinding;
import com.example.and_vidal.ui.workout.WorkoutViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel statsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);
        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fabStats = binding.fabStats;
        fabStats.setEnabled(false);
        fabStats.setOnClickListener(v ->
                Toast.makeText(getActivity(), "Stats", Toast.LENGTH_SHORT).show());

        final TextView textView = binding.textStats;
        statsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}