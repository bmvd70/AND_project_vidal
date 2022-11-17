package com.example.and_vidal.ui.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.databinding.FragmentStatsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class StatsFragment extends Fragment {

    private FragmentStatsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        StatsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(StatsViewModel.class);
        binding = FragmentStatsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fab_stats = binding.fabStats;

        fab_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Stats", Toast.LENGTH_SHORT).show();
            }
        });

        final TextView textView = binding.textStats;
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}