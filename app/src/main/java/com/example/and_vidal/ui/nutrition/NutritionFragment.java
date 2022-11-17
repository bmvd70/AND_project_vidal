package com.example.and_vidal.ui.nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.databinding.FragmentNutritionBinding;

public class NutritionFragment extends Fragment {

    private FragmentNutritionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NutritionViewModel dashboardViewModel =
                new ViewModelProvider(this).get(NutritionViewModel.class);

        binding = FragmentNutritionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textNutrition;
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}