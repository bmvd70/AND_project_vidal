package com.example.and_vidal.ui.nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.and_vidal.databinding.FragmentNutritionBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NutritionFragment extends Fragment {

    private FragmentNutritionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NutritionViewModel nutritionViewModel =
                new ViewModelProvider(this).get(NutritionViewModel.class);
        binding = FragmentNutritionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        FloatingActionButton fabNutrition = binding.fabNutrition;
        fabNutrition.setEnabled(false);
        fabNutrition.setOnClickListener(v ->
                Toast.makeText(getActivity(), "FAB_nurt was clicked", Toast.LENGTH_SHORT).show());

        final TextView textView = binding.textNutrition;
        nutritionViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}